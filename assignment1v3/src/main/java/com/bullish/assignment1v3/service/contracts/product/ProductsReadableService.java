package com.bullish.assignment1v3.service.contracts.product;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Product;

public interface ProductsReadableService {
    public ResponseEntity<List<Product>> readAllProducts();
}
