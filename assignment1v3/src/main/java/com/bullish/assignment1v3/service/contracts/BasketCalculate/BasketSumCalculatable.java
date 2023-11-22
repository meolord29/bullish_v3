package com.bullish.assignment1v3.service.contracts.BasketCalculate;

import java.util.List;

import com.bullish.assignment1v3.model.store.Basket;

public interface BasketSumCalculatable {

    public Double calculateBasketSum(List<Basket> baskets);

}
