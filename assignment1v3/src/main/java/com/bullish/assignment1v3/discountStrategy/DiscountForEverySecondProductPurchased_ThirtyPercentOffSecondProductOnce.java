package com.bullish.assignment1v3.discountStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class DiscountForEverySecondProductPurchased_ThirtyPercentOffSecondProductOnce {

    public Double getDiscount(ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap){

        Double discount = 0d;
        for (Map.Entry<String, ConcurrentHashMap<String, Double>> set : basketHashMap.entrySet()) {
            if (set.getValue().get("total") >= 2){
                discount += set.getValue().get("price")*0.3;
            }
        }
        return discount;
    }   

}
