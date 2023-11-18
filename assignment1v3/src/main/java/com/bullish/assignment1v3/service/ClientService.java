package com.bullish.assignment1v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.repository.ProductRepository;
import com.bullish.assignment1v3.service.contracts.AddableConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.AddableToBasketService;
import com.bullish.assignment1v3.service.contracts.BasketReadableService;
import com.bullish.assignment1v3.service.contracts.ProductReadableService;
import com.bullish.assignment1v3.service.contracts.ReadableConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.RemovableFromBasketService;
import com.bullish.assignment1v3.service.contracts.UpdatableBasketService;

@Service
public class ClientService 
implements ProductReadableService, 
BasketReadableService, RemovableFromBasketService, AddableToBasketService, UpdatableBasketService, 
AddableConfirmedPurchaseService, ReadableConfirmedPurchaseService{

    // CRUD METHODS - Client is able to do all of the CRUD methods within the basket
    @Autowired
	private ProductRepository productRepository;

    @Autowired
	private BasketRepository basketRepository;

    @Autowired 
    private ConfirmedPurchaseRepository confirmedPurchaseRepository;


    @Override
    public Product readProduct(ProductRepository productRepository) {// Read for Product Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readProduct'");
    }


    @Override
    public void updateBasket(BasketRepository basketRepository) {// CR_U_D for Basket Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBasket'");
    }


    @Override
    public void addToBasket(Product product) {// _C_RUD for Basket Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToBasket'");
    }

    @Override
    public void removeFromBasket(BasketRepository basketRepository) {// CRU_D_ for Basket Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromBasket'");
    }


    @Override
    public Basket readBasket(BasketRepository basketRepository) { // C_R_UD for Basket Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readBasket'");
    }


    @Override
    public ConfirmedPurchaseRepository readConfirmedPurchase(ConfirmedPurchaseRepository confirmedPurchaseRepository) { 
        // C_R_UD for Basket Table

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readConfirmedPurchase'");
    }


    @Override
    public void addConfirmedPurchase(ConfirmedPurchaseRepository confirmedPurchaseRepository) {
        // _C_RUD for Basket Table

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addConfirmedPurchase'");
    }
    
}
