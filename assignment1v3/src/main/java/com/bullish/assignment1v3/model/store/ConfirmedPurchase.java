package com.bullish.assignment1v3.model.store;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "ConfirmedPurchase")
@Table(name = "confirmedPurchase")
public class ConfirmedPurchase extends AbstractBasket{
    
    @Autowired
    public ConfirmedPurchase(String username, String productName, Integer totalSelected){
        super(username, productName, totalSelected);
    }

    @Autowired
    public ConfirmedPurchase(){
        super();
    }
    
}
