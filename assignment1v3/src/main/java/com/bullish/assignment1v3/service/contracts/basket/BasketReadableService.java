package com.bullish.assignment1v3.service.contracts.basket;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.repository.BasketRepository;

public interface BasketReadableService {

    public Optional<Basket> readBasket(String clientUsername);

}
