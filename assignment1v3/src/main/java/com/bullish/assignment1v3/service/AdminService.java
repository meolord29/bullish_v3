package com.bullish.assignment1v3.service;

import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Product;

@Service
public class AdminService implements ProductAddableService, ProductReadableService, ProductUpdatableService, ProductRemovableService{

    // CRUD METHODS - Admin is able to do all of the CRUD methods within the system

    @Override
    public void removeProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeProduct'");
    }

    @Override
    public void updateProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public Product readProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readProduct'");
    }

    @Override
    public void addProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProduct'");
    }

    

    
    
}
