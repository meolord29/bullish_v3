package com.bullish.assignment1v3.service;

import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.repository.ProductRepository;
import com.bullish.assignment1v3.service.contracts.ProductAddableService;
import com.bullish.assignment1v3.service.contracts.ProductReadableService;
import com.bullish.assignment1v3.service.contracts.ProductRemovableService;
import com.bullish.assignment1v3.service.contracts.ProductUpdatableService;

@Service
public class AdminService implements ProductAddableService, ProductReadableService, ProductUpdatableService, ProductRemovableService{

    // CRUD METHODS - Admin is able to do all of the CRUD methods within the system

    @Override
    public void removeProduct(ProductRepository productRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeProduct'");
    }

    @Override
    public void updateProduct(ProductRepository productRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public Product readProduct(ProductRepository productRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readProduct'");
    }

    @Override
    public void addProduct(ProductRepository productRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
    }

    

    

    
    
}
