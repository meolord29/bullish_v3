package com.bullish.assignment1v3.service.contracts;

import com.bullish.assignment1v3.repository.ProductRepository;

public interface ProductRemovableService { // CRUD - Delete
    
    public void removeProduct(ProductRepository productRepository);

}
