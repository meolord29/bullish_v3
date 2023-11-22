package com.bullish.assignment1v3.service.contracts.BasketCalculate;

import java.util.List;

import com.bullish.assignment1v3.model.store.Basket;

public interface BasketSumDiscountable {
    public Double CalculateDiscounted(List<Basket> basket);
}
