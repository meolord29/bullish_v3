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

    // Client can only view what products exist -> R of CRUD within the Library of PRODUCTS
    // Client can fully manage their Basket -> All CRUD Functionalities within the BASKET
    // Client can buy and review products that are in the basket -> CR of CRUD within the CONFIRMED PURCHASE of the basket

    @Autowired
	private ProductRepository productRepository;

    @Autowired
	private BasketRepository basketRepository;

    @Autowired 
    private ConfirmedPurchaseRepository confirmedPurchaseRepository;


    @Override
    public Optional<Product> readProduct(String productName) {// Read for Product Table
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
        // First: Check if Product is a non zero count in the products table, else abort the method
        // Second: method checks if product exists within the basket, else abort the method
        // Third: method requests Admin Service to delete product from the product Table with specified amount
        // Forth: method calls the updateBasket to update the counter of specific product counter
        // Fifth: method adds product to confirmedPurchase table -> update method with +n


        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addConfirmedPurchase'");
    }
    
}
