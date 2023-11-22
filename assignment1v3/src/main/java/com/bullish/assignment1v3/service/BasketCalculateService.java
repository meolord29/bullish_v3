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
public class BasketCalculateService implements BasketSumCalculatable{

    // DISCOUNT WIRING 
    @Autowired
    private DiscountForEverySecondProductPurchased_ThirtyPercentOffSecondProductOnce stratOne;

    @Autowired
    private DiscountForHavingThreeDifferentProducts_TenPercentOffAllMostPriciest stratTwo;
    //

    @Autowired
    private ProductService productService;

    private ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> getHashmapOfBasket(List<Basket> basket) {

        ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap = 
        new ConcurrentHashMap<String, ConcurrentHashMap<String, Double>>();

        Double price = 0d;
        for(Basket basketItem : basket){
            String productName = basketItem.getProductName();
            Double total = 0d + basketItem.getTotal();

            double individual_discount = productService.readProduct(basketItem.getProductName()).get().getDiscount();

            if (individual_discount != 0d){
                price = productService.readProduct(basketItem.getProductName()).get().getPrice() * (1-individual_discount);
            }
            else{
                price = productService.readProduct(basketItem.getProductName()).get().getPrice();
            }
            ConcurrentHashMap<String, Double> productInformation = new ConcurrentHashMap<String, Double>();

            productInformation.put("total", total);
            productInformation.put("price", price);

            basketHashMap.put(productName, productInformation);
        }
        return basketHashMap;
    }

    private Double calculateBasketSumOwed(List<Basket> baskets){
        Double totalSum = 0d;


        for(Basket basketItem : baskets){
            Product product = productService.readProduct(basketItem.getProductName()).get();

            double individual_discount = product.getDiscount();

            if (individual_discount != 0d){
                totalSum += productService.readProduct(basketItem.getProductName()).get().getPrice() * (1-individual_discount) * basketItem.getTotal();
            }
            else{
                totalSum += productService.readProduct(basketItem.getProductName()).get().getPrice() * basketItem.getTotal();
            }

            //totalSum += product.getPrice() * basketItem.getTotal();
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
    public Double calculateBasketSum(List<Basket> baskets) {
        return calculateBasketSumOwed(baskets) - calculateDiscounted(baskets);

    }

}
