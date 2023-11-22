package com.bullish.assignment1v3.service.contracts.BasketCalculate;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Basket;

public interface BasketSumCalculatable {

    public ResponseEntity<Double> calculateBasketSum(List<Basket> baskets);

}
