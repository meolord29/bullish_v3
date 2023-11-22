package com.bullish.assignment1v3.service.contracts.basket;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Basket;

public interface AddableToBasketService {

    public ResponseEntity<Basket> addToBasket(Basket basket);

}
