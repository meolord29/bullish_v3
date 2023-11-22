package com.bullish.assignment1v3.service.contracts.basket;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Basket;

public interface BasketAllReadableService {

    public ResponseEntity<List<Basket>> readBasketAll(String clientUsername);

}
