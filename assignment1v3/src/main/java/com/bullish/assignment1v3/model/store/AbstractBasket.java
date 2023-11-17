package com.bullish.assignment1v3.model.store;

import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

@Getter
abstract class AbstractBasket {

    public Float total_price;

    public ConcurrentHashMap<Product, Float> basket;
}
