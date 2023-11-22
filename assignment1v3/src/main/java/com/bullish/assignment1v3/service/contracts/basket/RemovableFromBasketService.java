package com.bullish.assignment1v3.service.contracts.basket;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;

public interface RemovableFromBasketService {

    public ResponseEntity<Basket> removeFromBasket(Client client, Product product);

}
