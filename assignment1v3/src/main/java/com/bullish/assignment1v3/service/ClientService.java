package com.bullish.assignment1v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.repository.ProductRepository;
import com.bullish.assignment1v3.service.contracts.AddableToBasketService;
import com.bullish.assignment1v3.service.contracts.BasketReadableService;
import com.bullish.assignment1v3.service.contracts.ProductReadableService;
import com.bullish.assignment1v3.service.contracts.RemovableFromBasketService;
import com.bullish.assignment1v3.service.contracts.UpdatableBasketService;

@Service
public class ClientService implements ProductReadableService, BasketReadableService, RemovableFromBasketService, AddableToBasketService, UpdatableBasketService {

    // CRUD METHODS - Client is able to do all of the CRUD methods within the basket
    @Autowired
	private ProductRepository productRepository;

    @Autowired
	private BasketRepository basketRepository;


    @Override
    public Product readProduct(ProductRepository productRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readProduct'");
    }


    @Override
    public void updateBasket(BasketRepository basketRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBasket'");
    }


    @Override
    public void addToBasket(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToBasket'");
    }


    @Override
    public void removeFromBasket(BasketRepository basketRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromBasket'");
    }


    @Override
    public Basket readBasket(BasketRepository basketRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readBasket'");
    }
    
}
