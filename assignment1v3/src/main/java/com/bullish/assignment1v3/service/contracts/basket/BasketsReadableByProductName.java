package com.bullish.assignment1v3.service.contracts.basket;

import java.util.List;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;

public interface BasketsReadableByProductName {
    List<Basket> readAllBasketsByProductName(Product product);
}
