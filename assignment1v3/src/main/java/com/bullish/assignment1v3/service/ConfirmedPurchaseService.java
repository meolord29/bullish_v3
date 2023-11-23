package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.ConfirmedPurchase;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.AddableConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.ReadableAllConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.ReadableConfirmedPurchaseService;

import jakarta.transaction.Transactional;

public class ConfirmedPurchaseService implements
AddableConfirmedPurchaseService, ReadableAllConfirmedPurchaseService, ReadableConfirmedPurchaseService{

    private ConfirmedPurchaseRepository confirmedPurchaseRepository;

    @Autowired
    private BasketService basketService;

    public ConfirmedPurchaseService(ConfirmedPurchaseRepository confirmedPurchaseRepository) {
        this.confirmedPurchaseRepository = confirmedPurchaseRepository;
    }

    @Override
    public Optional<ConfirmedPurchase> readConfirmedPurchase(ConfirmedPurchase confirmedPurchaseUpdated) {
        return confirmedPurchaseRepository.findByUsernameAndProductName(confirmedPurchaseUpdated.getUsername(), confirmedPurchaseUpdated.getProductName());
    }

    @Override
    public ResponseEntity<List<ConfirmedPurchase>> readAllConfirmedPurchase(String clientUsername) {
        List<ConfirmedPurchase> confirmedPurchaseAll = confirmedPurchaseRepository.findByUsernameIs(clientUsername);

        if (confirmedPurchaseAll != null && !confirmedPurchaseAll.isEmpty()) {
            return new ResponseEntity<>(confirmedPurchaseAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ConfirmedPurchase> addConfirmedPurchase(ConfirmedPurchase confirmedPurchase) {
        Optional<ConfirmedPurchase> confirmedPurchaseOpt = confirmedPurchaseRepository.findByUsernameAndProductName(
            confirmedPurchase.getUsername(),  confirmedPurchase.getProductName());

        if (confirmedPurchaseOpt.isPresent()) {
            ConfirmedPurchase confirmedPurchaseUpdated = confirmedPurchaseOpt.get();

            basketService.removeFromBasket(
                new Basket(
                    confirmedPurchaseUpdated.getUsername(), 
                    confirmedPurchaseUpdated.getProductName(), 
                    confirmedPurchaseUpdated.getTotal()));

            confirmedPurchaseUpdated.setTotal(confirmedPurchaseUpdated.getTotal()+confirmedPurchase.getTotal());

            return updateConfirmedPurchase(confirmedPurchase);


        } 
        else {
            ConfirmedPurchase newConfirmedPurchase = new ConfirmedPurchase();

            newConfirmedPurchase.setUsername(confirmedPurchase.getUsername());
            newConfirmedPurchase.setTotal(confirmedPurchase.getTotal());
            newConfirmedPurchase.setProductName(confirmedPurchase.getProductName()); 
            confirmedPurchaseRepository.save(newConfirmedPurchase);

            basketService.removeFromBasket(
                new Basket(
                    newConfirmedPurchase.getUsername(), 
                    newConfirmedPurchase.getProductName(), 
                    newConfirmedPurchase.getTotal()));

            return new ResponseEntity<>(newConfirmedPurchase, HttpStatus.CREATED);
        }


    }

    @Transactional
    private ResponseEntity<ConfirmedPurchase> updateConfirmedPurchase(ConfirmedPurchase confirmedPurchaseUpdated) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Optional<ConfirmedPurchase> confirmedPurchaseUpdatedOpt = readConfirmedPurchase(confirmedPurchaseUpdated);

        if (confirmedPurchaseUpdatedOpt.isPresent()) {
            ConfirmedPurchase confirmedPurchase = confirmedPurchaseUpdatedOpt.get();
            mapper.map(confirmedPurchaseUpdated, confirmedPurchase);
            confirmedPurchaseRepository.save(confirmedPurchase);
            return new ResponseEntity<>(confirmedPurchase, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
}
