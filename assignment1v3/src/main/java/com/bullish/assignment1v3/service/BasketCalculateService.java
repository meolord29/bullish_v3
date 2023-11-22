package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.discountStrategy.DiscountForEverySecondProductPurchased_ThirtyPercentOffSecondProductOnce;
import com.bullish.assignment1v3.discountStrategy.discountForHavingThreeDifferentProducts_TenPercentOffAllMostPriciest;
import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.service.contracts.BasketCalculate.BasketSumCalculatable;

@Service
public class BasketCalculateService implements BasketSumCalculatable{

    // DISCOUNT WIRING 
    @Autowired
    private DiscountForEverySecondProductPurchased_ThirtyPercentOffSecondProductOnce stratOne;

    @Autowired
    private discountForHavingThreeDifferentProducts_TenPercentOffAllMostPriciest stratTwo;
    //

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

    private Double calculateBasketSumOwed(List<Basket> basket){
        Double totalSum = 0d;
        for(Basket basketItem : basket){
            Product product = productService.readProduct(basketItem.getProductName()).get();
            totalSum += product.getPrice() * basketItem.getTotal();
        }
        return totalSum;
    }
    
    private Double calculateDiscounted(List<Basket> basket){
        ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap = getHashmapOfBasket(basket);

        Double total_discount = 0d;

        // can be later replaced with reflections, 
        // to just call upon the method of each class in the discount strategy package
        total_discount += stratOne.getDiscount(basketHashMap); // For every 2nd, once
        total_discount += stratTwo.getDiscount(basketHashMap); // For 5 of different kind

        return total_discount;
    }

    @Override
    public Double calculateBasketSum(List<Basket> basket) {
        
        return calculateBasketSumOwed(basket) - calculateDiscounted(basket);

    }

}
