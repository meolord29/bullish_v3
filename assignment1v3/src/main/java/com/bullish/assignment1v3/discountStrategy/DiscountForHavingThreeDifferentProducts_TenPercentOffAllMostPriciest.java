package com.bullish.assignment1v3.discountStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class DiscountForHavingThreeDifferentProducts_TenPercentOffAllMostPriciest {

    // Method to calculate discount based on the condition: 10% off on all most priciest products if there are at least three different products
    public Double getDiscount(ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap) {
        Double discount = 0d;

        // Check if the basket contains at least five different products
        if (basketHashMap.size() >= 5) {

            // Create a list to store prices of products with a total quantity greater than or equal to 1
            ArrayList<Double> productPriceList = new ArrayList<Double>();

            // Iterate through each product entry in the basket
            for (Map.Entry<String, ConcurrentHashMap<String, Double>> set : basketHashMap.entrySet()) {
                if (set.getValue().get("total") >= 1) {
                    // Add the price of the product to the list
                    productPriceList.add(set.getValue().get("price"));
                }
            }

            // Sort the product prices in descending order
            Collections.sort(productPriceList, Collections.reverseOrder());

            // Calculate the discount by applying a 10% discount on each of the most priciest products
            for (Double price : productPriceList) {
                discount += price * 0.1;
            }

            return discount;
        }

        return discount;
    }
}