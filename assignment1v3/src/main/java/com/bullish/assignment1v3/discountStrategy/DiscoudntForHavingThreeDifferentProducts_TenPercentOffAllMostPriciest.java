package com.bullish.assignment1v3.discountStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class DiscoudntForHavingThreeDifferentProducts_TenPercentOffAllMostPriciest {

    public Double getDiscount(ConcurrentHashMap<String, ConcurrentHashMap<String, Double>> basketHashMap)
        {
        Double discount = 0d;
        if (basketHashMap.size() >= 5){
            
            ArrayList<Double> product_price_list = new ArrayList<Double>();
            for (Map.Entry<String, ConcurrentHashMap<String, Double>> set : basketHashMap.entrySet()) {
                if (set.getValue().get("total") > 1){
                    product_price_list.add(set.getValue().get("price"));
                }
            }
            
            Collections.sort(product_price_list, Collections.reverseOrder()); 
            
            for (Double price : product_price_list){discount += price*0.1;}

            return discount;

        }
        return discount;
        
    }   
    
}
