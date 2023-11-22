package com.bullish.assignment1v3.service.contracts.basket;

import java.util.Optional;

import com.bullish.assignment1v3.model.store.Basket;

public interface BasketReadableService {
    public Optional<Basket> readBasket(Basket basket);
}
