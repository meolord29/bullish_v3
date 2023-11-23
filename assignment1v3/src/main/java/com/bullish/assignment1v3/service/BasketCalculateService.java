package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.discountStrategy.DiscountForEverySecondProductPurchased_ThirtyPercentOffSecondProductOnce;
import com.bullish.assignment1v3.discountStrategy.DiscountForHavingThreeDifferentProducts_TenPercentOffAllMostPriciest;
import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.service.contracts.BasketCalculate.BasketSumCalculatable;
@Service
public class BasketCalculateService implements BasketSumCalculatable {

    // DISCOUNT WIRING

    // Autowired discount strategies
    @Autowired
    private DiscountForEverySecondProductPurchased_ThirtyPercentOffSecondProductOnce stratOne;

    @Autowired
    private DiscountForHavingThreeDifferentProducts_TenPercentOffAllMostPriciest stratTwo;

    //

    @Autowired
    private ProductService productService;

    // Helper method to convert a list of Basket items into a nested ConcurrentHashMap
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> getHashmapOfBasket(List<Basket> basket) {

        ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap =
                new ConcurrentHashMap<String, ConcurrentHashMap<String, Double>>();

        Double price = 0d;

        // Iterate over each Basket item
        for (Basket basketItem : basket) {
            String productName = basketItem.getProductName();
            Double total = 0d + basketItem.getTotal();

            // Get individual discount for the product
            double individual_discount = productService.readProduct(basketItem.getProductName()).get().getDiscount();

            // Calculate the price considering the individual discount
            if (individual_discount != 0d) {
                price = productService.readProduct(basketItem.getProductName()).get().getPrice() * (1 - individual_discount);
            } else {
                price = productService.readProduct(basketItem.getProductName()).get().getPrice();
            }

            // Create a ConcurrentHashMap containing total and price, and add it to the outer map
            ConcurrentHashMap<String, Double> productInformation = new ConcurrentHashMap<String, Double>();
            productInformation.put("total", total);
            productInformation.put("price", price);
            basketHashMap.put(productName, productInformation);
        }
        return basketHashMap;
    }

    // Helper method to calculate the total sum owed for all products in the basket
    private Double calculateBasketSumOwed(List<Basket> baskets) {
        Double totalSum = 0d;

        // Iterate over each Basket item
        for (Basket basketItem : baskets) {
            Product product = productService.readProduct(basketItem.getProductName()).get();

            // Get individual discount for the product
            double individual_discount = product.getDiscount();

            // Calculate the total sum considering the individual discount
            if (individual_discount != 0d) {
                totalSum += productService.readProduct(basketItem.getProductName()).get().getPrice() * (1 - individual_discount) * basketItem.getTotal();
            } else {
                totalSum += productService.readProduct(basketItem.getProductName()).get().getPrice() * basketItem.getTotal();
            }
        }
        return totalSum;
    }

    // Helper method to calculate the total discounted amount based on applied discount strategies
    private Double calculateDiscounted(List<Basket> basket) {
        ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap = getHashmapOfBasket(basket);

        Double total_discount = 0d;

        // Calculate discounts using the wired discount strategies
        total_discount += stratOne.getDiscount(basketHashMap); // For every 2nd, once
        total_discount += stratTwo.getDiscount(basketHashMap); // For 5 of different kind

        return total_discount;
    }

    // Implementation of the BasketSumCalculatable interface
    @Override
    public Double calculateBasketSum(List<Basket> baskets) {
        // Subtract the total discounted amount from the total sum owed
        return calculateBasketSumOwed(baskets) - calculateDiscounted(baskets);
    }
}

