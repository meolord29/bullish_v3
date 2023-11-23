package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.ConfirmedPurchase;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.AddableConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.ReadableAllConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.ReadableConfirmedPurchaseService;

import jakarta.transaction.Transactional;
@Service
public class ConfirmedPurchaseService implements
        AddableConfirmedPurchaseService, ReadableAllConfirmedPurchaseService, ReadableConfirmedPurchaseService {

    // Repository for database operations
    private ConfirmedPurchaseRepository confirmedPurchaseRepository;

    // Autowired BasketService for managing basket contents
    @Autowired
    private BasketService basketService;

    // Constructor to inject dependencies
    public ConfirmedPurchaseService(ConfirmedPurchaseRepository confirmedPurchaseRepository) {
        this.confirmedPurchaseRepository = confirmedPurchaseRepository;
    }

    // Read a confirmed purchase by username and product name
    @Override
    public Optional<ConfirmedPurchase> readConfirmedPurchase(ConfirmedPurchase confirmedPurchaseUpdated) {
        return confirmedPurchaseRepository.findByUsernameAndProductName(
                confirmedPurchaseUpdated.getUsername(), confirmedPurchaseUpdated.getProductName());
    }

    // Read all confirmed purchases for a client
    @Override
    public ResponseEntity<List<ConfirmedPurchase>> readAllConfirmedPurchase(String clientUsername) {
        // Retrieve all confirmed purchases for a given client
        List<ConfirmedPurchase> confirmedPurchaseAll = confirmedPurchaseRepository.findByUsernameIs(clientUsername);

        if (confirmedPurchaseAll != null && !confirmedPurchaseAll.isEmpty()) {
            // If confirmed purchases found, return them with an OK status
            return new ResponseEntity<>(confirmedPurchaseAll, HttpStatus.OK);
        } else {
            // If no confirmed purchases found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new confirmed purchase or update an existing one
    @Override
    public ResponseEntity<ConfirmedPurchase> addConfirmedPurchase(ConfirmedPurchase confirmedPurchase) {
        // Check if a confirmed purchase with the same username and product name already exists
        Optional<ConfirmedPurchase> confirmedPurchaseOpt = confirmedPurchaseRepository.findByUsernameAndProductName(
                confirmedPurchase.getUsername(), confirmedPurchase.getProductName());

        if (confirmedPurchaseOpt.isPresent()) {
            // If the confirmed purchase already exists, update the total and update the basket
            ConfirmedPurchase confirmedPurchaseUpdated = confirmedPurchaseOpt.get();
            basketService.removeFromBasket(
                    new Basket(
                            confirmedPurchaseUpdated.getUsername(),
                            confirmedPurchaseUpdated.getProductName(),
                            confirmedPurchaseUpdated.getTotal()));

            confirmedPurchaseUpdated.setTotal(confirmedPurchaseUpdated.getTotal() + confirmedPurchase.getTotal());

            return updateConfirmedPurchase(confirmedPurchaseUpdated);
        } else {
            // If the confirmed purchase does not exist, create a new one and update the basket
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

    // Helper method to update an existing confirmed purchase
    @Transactional
    private ResponseEntity<ConfirmedPurchase> updateConfirmedPurchase(ConfirmedPurchase confirmedPurchaseUpdated) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        // Check if the confirmed purchase exists
        Optional<ConfirmedPurchase> confirmedPurchaseUpdatedOpt = readConfirmedPurchase(confirmedPurchaseUpdated);

        if (confirmedPurchaseUpdatedOpt.isPresent()) {
            // If the confirmed purchase exists, update its fields and save to the database
            ConfirmedPurchase confirmedPurchase = confirmedPurchaseUpdatedOpt.get();
            mapper.map(confirmedPurchaseUpdated, confirmedPurchase);
            confirmedPurchaseRepository.save(confirmedPurchase);
            return new ResponseEntity<>(confirmedPurchase, HttpStatus.OK);
        } else {
            // If the confirmed purchase not found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
