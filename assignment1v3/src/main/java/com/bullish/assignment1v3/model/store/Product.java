package com.bullish.assignment1v3.model.store;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Product")
@Table(name = "product")
public class Product extends AbstractItem{

    @Autowired
    public Product(String name, Double price, Double discount, Integer total){
        super(name, price, discount, total);
    }
    @Autowired
    public Product() {
        super();
    }
    
    

}
