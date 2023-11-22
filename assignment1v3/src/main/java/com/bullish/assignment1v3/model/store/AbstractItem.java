package com.bullish.assignment1v3.model.store;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Double price;

    @Column(name="discount")
    private Double discount;

    @Column(name="total")
    private Integer total;
    
    public AbstractItem(String name, Double price, Double discount, Integer total){
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.total = total;
    }

    public AbstractItem(){
        
    }

}
