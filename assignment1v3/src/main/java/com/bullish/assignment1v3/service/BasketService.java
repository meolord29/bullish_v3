package com.bullish.assignment1v3.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.service.contracts.basket.AddableToBasketService;
import com.bullish.assignment1v3.service.contracts.basket.BasketReadableService;
import com.bullish.assignment1v3.service.contracts.basket.RemovableFromBasketService;
import jakarta.transaction.Transactional;

@Service
public class BasketService implements
BasketReadableService, RemovableFromBasketService, AddableToBasketService{

    private BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    private ResponseEntity<Basket> updateBasket(Basket basketUpdated) {
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

    @Override
    public Optional<Basket> readBasket(String clientUsername) {
        return basketRepository.findByUsername(clientUsername);
    }

    @Override
    @Transactional
    public ResponseEntity<Basket> addToBasket(Client client, Product product) {

        Optional<Basket> basketProductOpt = basketRepository.findByUsernameAndProductName(client.getUsername(), product.getName());

        if (basketProductOpt.isPresent()) {
            Basket basketUpdated = new Basket(client.getUsername(), product.getName(), basketProductOpt.get().getTotalSelected()+product.getTotal());
            return updateBasket(basketUpdated);

        } else {
            Basket newBasket = new Basket(client.getUsername(), product.getName(), product.getTotal());
            basketRepository.save(newBasket);
            return new ResponseEntity<>(newBasket, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Basket> removeFromBasket(Client client, Product product) {
        Optional<Basket> basketProductOpt = basketRepository.findByUsernameAndProductName(client.getUsername(), product.getName());

        if (basketProductOpt.isPresent()) {
            basketRepository.delete(basketProductOpt.get());
            return new ResponseEntity<>(basketProductOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
