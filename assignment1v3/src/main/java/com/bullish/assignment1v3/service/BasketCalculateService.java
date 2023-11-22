package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.service.contracts.BasketCalculate.BasketSumCalculatable;

@Service
public class BasketCalculateService implements BasketSumCalculatable, BasketSumOwedCalculatable {

    @Autowired
    private ProductService productService;

    @Override
    public Double calculateBasketSum(List<Basket> basket) {

        Double totalSum = 0d;
        for(Basket basketItem : basket){
            Product product = productService.readProduct(basketItem.getProductName()).get();
            totalSum += product.getPrice() * basketItem.getTotal();
        }
        return totalSum;

    }

    @Override
    public Double CalculateBasketSumOwed(List<Basket> basket){
        Double totalSum = 0d;
        for(Basket basketItem : basket){
            Product product = productService.readProduct(basketItem.getProductName()).get();
            totalSum += product.getPrice() * basketItem.getTotal();
        }
        return totalSum;
    }
    


}
