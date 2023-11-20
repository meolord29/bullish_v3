package com.bullish.assignment1v3.service.contracts;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.repository.ProductRepository;

public interface ProductUpdatableService { // CRUD - Update
    
    public void updateProduct(String name, Product productUpdated);

}
