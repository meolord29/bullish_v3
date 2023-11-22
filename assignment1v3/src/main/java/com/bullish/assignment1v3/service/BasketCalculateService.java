package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.service.contracts.BasketCalculate.BasketSumCalculatable;
import com.bullish.assignment1v3.service.contracts.BasketCalculate.BasketSumDiscountable;
import com.bullish.assignment1v3.service.contracts.BasketCalculate.BasketSumOwedCalculatable;

@Service
public class BasketCalculateService implements BasketSumCalculatable, BasketSumOwedCalculatable, BasketSumDiscountable {

    @Autowired
    private ProductService productService;

    private ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> getHashmapOfBasket(List<Basket> basket) {

        ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap = 
        new ConcurrentHashMap<String, ConcurrentHashMap<String, Double>>();

        for(Basket basketItem : basket){
            String productName = basketItem.getProductName();
            Double total = 0d + basketItem.getTotal();
            Double price = productService.readProduct(basketItem.getProductName()).get().getPrice();
            ConcurrentHashMap<String, Double> productInformation = new ConcurrentHashMap<String, Double>();

            productInformation.put("total", total);
            productInformation.put("price", price);

            basketHashMap.put(productName, productInformation);
        }
        return basketHashMap;
    }

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
    
    @Override
    public Double CalculateDiscounted(List<Basket> basket){
        Double totalSum = 0d;
        for(Basket basketItem : basket){
            Product product = productService.readProduct(basketItem.getProductName()).get();
            totalSum += product.getPrice() * basketItem.getTotal();
        }
        return totalSum;
    }

}
