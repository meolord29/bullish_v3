package com.bullish.assignment1v3.service.contracts;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.repository.ProductRepository;

public interface ProductReadableService { // CRUD - Read
    
    public Product readProduct(ProductRepository productRepository);
    
}
