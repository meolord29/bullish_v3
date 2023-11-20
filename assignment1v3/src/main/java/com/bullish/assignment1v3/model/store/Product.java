package com.bullish.assignment1v3.model.store;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "Product")
@Table(name = "product")
public class Product extends AbstractItem{

    @Autowired
    public Product(String name, Float price, Float discount, Integer totalAvailable){
        super(name, price, discount, totalAvailable);
    }
    @Autowired
    public Product() {
        super();
    }
    
    

}
