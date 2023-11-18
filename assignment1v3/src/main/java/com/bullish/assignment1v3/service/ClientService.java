package com.bullish.assignment1v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.repository.ProductRepository;

@Service
public class ClientService implements ProductReadableService, BasketReadableService, RemovableFromBasketService, AddableToBasketService, UpdatableBasketService {

    // CRUD METHODS - Client is able to do all of the CRUD methods within the basket
    @Autowired
	private ProductRepository productRepository;


    @Override
    public Product readProduct(ProductRepository productRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readProduct'");
    }
    
}
