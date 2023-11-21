package com.bullish.assignment1v3.service.contracts.product;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Product;

public interface ProductAddableService { // CRUD - Create
    
    public ResponseEntity<Product> addProduct(Product product);

}
