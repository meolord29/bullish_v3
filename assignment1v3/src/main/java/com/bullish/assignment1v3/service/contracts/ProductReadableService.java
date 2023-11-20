package com.bullish.assignment1v3.service.contracts;

import java.util.Optional;

import com.bullish.assignment1v3.model.store.Product;

public interface ProductReadableService { // CRUD - Read
    
    public Optional<Product> readProduct(String productName);
    
}
