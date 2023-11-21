package com.bullish.assignment1v3.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.repository.BasketRepository;

import jakarta.transaction.Transactional;

public class BasketService {

    private BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    public ResponseEntity<Basket> updateBasket(Basket basketUpdated) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Optional<Basket> basketOpt = readBasket(basketUpdated.getUsername());

        if (basketOpt.isPresent()) {
            Basket basket = basketOpt.get();
            mapper.map(basketUpdated, basket);
            basketRepository.save(basket);
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Basket> readBasketGetCertainProduct(String clientUsername, String productName) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);


        Optional<Basket> basketOpt = basketRepository.findByUsername(clientUsername);

        if (basketOpt.isPresent()) {

            Optional<Basket> basketCertainProductOpt = basketRepository.findByUsernameAndProductName(basketOpt.get().getUsername(), productName);
            
            if (basketCertainProductOpt.isPresent()) {
                Basket basket = basketCertainProductOpt.get();
                return new ResponseEntity<>(basket, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Optional<Basket> readBasket(String clientUsername) {
        Optional<Basket> basketOpt = basketRepository.findByUsername(clientUsername);
        return basketOpt;
    }

    @Transactional
    public ResponseEntity<Basket> addBasket(Basket basket) {
        Optional<Basket> basketOpt = readBasket(basket.getUsername());

        if (basketOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            basketRepository.save(basket);
            return new ResponseEntity<>(basket, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Basket> deleteBasket(Basket basket) {
        Optional<Basket> basketOpt = readBasket(basket.getUsername());

        if (basketOpt.isPresent()) {
            basketRepository.delete(basketOpt.get());
            return new ResponseEntity<>(basketOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
