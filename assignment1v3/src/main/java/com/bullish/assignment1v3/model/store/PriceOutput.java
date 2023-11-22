package com.bullish.assignment1v3.model.store;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class PriceOutput{
    private String username;
    private Double priceTotal;
    
    public PriceOutput(String username, Double priceTotal){
        this.username = username;
        this.priceTotal = priceTotal;
    }

    public PriceOutput(){
    }
    
}
