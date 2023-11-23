package com.bullish.assignment1v3.discountStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class DiscountForEverySecondProductPurchased_ThirtyPercentOffSecondProductOnce {

    // Method to calculate discount based on the condition: 30% off for every second product, once
    public Double getDiscount(ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap){

        Double discount = 0d;

        // Iterate through each product entry in the basket
        for (Map.Entry<String, ConcurrentHashMap<String, Double>> set : basketHashMap.entrySet()) {
            
            // Check if the total quantity of the product is greater than or equal to 2
            if (set.getValue().get("total") >= 2){
                
                // Apply a 30% discount on the price of the second product
                discount += set.getValue().get("price") * 0.3;
            }
        }
        return discount;
    }   
}
