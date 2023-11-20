package com.bullish.assignment1v3.model.store;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public abstract class AbstractBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="productName")
    private String productName;

    @Column(name="totalSelected")
    private Integer totalSelected;

    public AbstractBasket(String username, String productName, Integer totalSelected){
        this.username = username;
        this.productName = productName;
        this.totalSelected = totalSelected;
    }

    public AbstractBasket(){
        
    }
}
