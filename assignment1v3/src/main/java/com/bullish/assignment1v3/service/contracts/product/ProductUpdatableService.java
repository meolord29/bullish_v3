package com.bullish.assignment1v3.service.contracts.product;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;

public interface ProductUpdatableService { // CRUD - Update
    
    public ResponseEntity<Product> updateProduct(Product product);

}
