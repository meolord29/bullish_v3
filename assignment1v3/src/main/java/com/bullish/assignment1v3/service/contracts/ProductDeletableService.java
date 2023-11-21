package com.bullish.assignment1v3.service.contracts;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Product;

public interface ProductDeletableService { // CRUD - Delete
    
    public ResponseEntity<Product> deleteProduct(Product product);

}
