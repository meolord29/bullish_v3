package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.service.contracts.basket.AddableToBasketService;
import com.bullish.assignment1v3.service.contracts.basket.BasketAllReadableService;
import com.bullish.assignment1v3.service.contracts.basket.BasketReadableService;
import com.bullish.assignment1v3.service.contracts.basket.RemovableFromBasketService;

import jakarta.transaction.Transactional;

@Service
public class BasketService implements
BasketAllReadableService, RemovableFromBasketService, AddableToBasketService, BasketReadableService{

    private BasketRepository basketRepository;

    @Autowired
    private BasketCalculateService basketCalculateService;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    private ResponseEntity<Basket> updateBasket(Basket basketUpdated) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Optional<Basket> basketOpt = readBasket(basketUpdated);

        if (basketOpt.isPresent()) {
            Basket basket = basketOpt.get();
            mapper.map(basketUpdated, basket);
            basketRepository.save(basket);
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Basket>> readBasketAll(String clientUsername) {

        List<Basket> basketAll = basketRepository.findByUsernameIs(clientUsername);

        if (basketAll != null && !basketAll.isEmpty()) {
            return new ResponseEntity<>(basketAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Optional<Basket> readBasket(Basket basket) {
        return basketRepository.findByUsernameAndProductName(basket.getUsername(), basket.getProductName());
    }

    @Override
    @Transactional
    public ResponseEntity<Basket> addToBasket(Basket basket) {

        Optional<Basket> basketProductOpt = basketRepository.findByUsernameAndProductName(basket.getUsername(), basket.getProductName());

        if (basketProductOpt.isPresent()) {
            Basket basketUpdated = basketProductOpt.get();

            basketUpdated.setTotal(basketUpdated.getTotal()+basket.getTotal());

            return updateBasket(basketUpdated);

        } 
        else {
            Basket newBasket = new Basket();

            newBasket.setUsername(basket.getUsername());
            newBasket.setTotal(basket.getTotal());
            newBasket.setProductName(basket.getProductName()); 
            basketRepository.save(newBasket);

            return new ResponseEntity<>(newBasket, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Basket> removeFromBasket(Basket basket) {
        Optional<Basket> basketProductOpt = basketRepository.findByUsernameAndProductName(basket.getUsername(), basket.getProductName());

        if (basketProductOpt.isPresent()) {

            if (basket.getTotal() == basketProductOpt.get().getTotal()){ // if all of the product is deleted
                basketRepository.delete(basketProductOpt.get());
                return new ResponseEntity<>(basketProductOpt.get(), HttpStatus.OK);
            }

            else if (basket.getTotal() <= basketProductOpt.get().getTotal()){ // if only some of the product is deleted
                Basket basketUpdated = basketProductOpt.get();

                basketUpdated.setTotal(basketUpdated.getTotal()-basket.getTotal());

                return updateBasket(basketUpdated);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<Double> getTotalPrice(String username){
        return new ResponseEntity<>(basketCalculateService.calculateBasketSum(readBasketAll(username).getBody()), HttpStatus.OK);
    }
    
}
