package com.bullish.assignment1v3.model.store;

import java.util.concurrent.ConcurrentHashMap;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="price")
    private Float price;

    @Column(name="username")
    private String clientUsername;

    @Column(name="productName")
    private String productName;

}
