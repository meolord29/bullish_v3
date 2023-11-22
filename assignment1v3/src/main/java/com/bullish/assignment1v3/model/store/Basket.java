package com.bullish.assignment1v3.model.store;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity(name = "Basket")
@Table(name = "basket")
public class Basket extends AbstractBasket{

    @Autowired
    public Basket(String username, String productName, Integer total){
        super(username, productName, total);
    }

    @Autowired
    public Basket(){
        super();
    }

}
