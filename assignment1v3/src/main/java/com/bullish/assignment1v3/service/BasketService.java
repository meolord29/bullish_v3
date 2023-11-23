package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.utility.PriceOutput;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.service.contracts.basket.AddableToBasketService;
import com.bullish.assignment1v3.service.contracts.basket.BasketAllReadableService;
import com.bullish.assignment1v3.service.contracts.basket.BasketReadableService;
import com.bullish.assignment1v3.service.contracts.basket.BasketsReadableByProductName;
import com.bullish.assignment1v3.service.contracts.basket.RemovableFromBasketService;

import jakarta.transaction.Transactional;

@Service
public class BasketService implements
BasketAllReadableService, RemovableFromBasketService, AddableToBasketService, BasketReadableService, BasketsReadableByProductName {

    // Basket repository for database operations
    private BasketRepository basketRepository;

    // Autowired BasketCalculateService for calculating basket totals and discounts
    @Autowired
    private BasketCalculateService basketCalculateService;

    // Constructor to inject dependencies
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    // Update a basket in the database
    @Transactional
    private ResponseEntity<Basket> updateBasket(Basket basketUpdated) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        // Check if the basket exists
        Optional<Basket> basketOpt = readBasket(basketUpdated);

        if (basketOpt.isPresent()) {
            // If basket exists, update its fields and save to the database
            Basket basket = basketOpt.get();
            mapper.map(basketUpdated, basket);
            basketRepository.save(basket);
            return new ResponseEntity<>(basket, HttpStatus.OK);
        } else {
            // If basket not found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Implementation of BasketAllReadableService interface
    @Override
    public ResponseEntity<List<Basket>> readBasketAll(String clientUsername) {
        // Retrieve all baskets for a given client
        List<Basket> basketAll = basketRepository.findByUsernameIs(clientUsername);

        if (basketAll != null && !basketAll.isEmpty()) {
            // If baskets found, return them with an OK status
            return new ResponseEntity<>(basketAll, HttpStatus.OK);
        } else {
            // If no baskets found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Implementation of BasketReadableService interface
    @Override
    public Optional<Basket> readBasket(Basket basket) {
        // Retrieve a basket based on username and product name
        return basketRepository.findByUsernameAndProductName(basket.getUsername(), basket.getProductName());
    }

    // Implementation of BasketsReadableByProductName interface
    @Override
    public List<Basket> readAllBasketsByProductName(Product product) {
        // Retrieve all baskets containing a specific product
        return basketRepository.findByProductName(product.getName());
    }

    // Implementation of AddableToBasketService interface
    @Override
    @Transactional
    public ResponseEntity<Basket> addToBasket(Basket basket) {
        // Check if the product is already in the basket
        Optional<Basket> basketProductOpt = basketRepository.findByUsernameAndProductName(basket.getUsername(), basket.getProductName());

        if (basketProductOpt.isPresent()) {
            // If the product is already in the basket, update the total
            Basket basketUpdated = basketProductOpt.get();
            basketUpdated.setTotal(basketUpdated.getTotal() + basket.getTotal());
            return updateBasket(basketUpdated);
        } else {
            // If the product is not in the basket, create a new basket entry
            Basket newBasket = new Basket();
            newBasket.setUsername(basket.getUsername());
            newBasket.setTotal(basket.getTotal());
            newBasket.setProductName(basket.getProductName());
            basketRepository.save(newBasket);
            return new ResponseEntity<>(newBasket, HttpStatus.CREATED);
        }
    }

    // Implementation of RemovableFromBasketService interface
    @Override
    public ResponseEntity<Basket> removeFromBasket(Basket basket) {
        // Check if the product is in the basket
        Optional<Basket> basketProductOpt = basketRepository.findByUsernameAndProductName(basket.getUsername(), basket.getProductName());

        if (basketProductOpt.isPresent()) {
            // If the product is in the basket, determine if all or part of it should be removed
            if (basket.getTotal() == basketProductOpt.get().getTotal()) {
                // If all of the product is deleted, delete the entire basket entry
                basketRepository.delete(basketProductOpt.get());
                return new ResponseEntity<>(basketProductOpt.get(), HttpStatus.OK);
            } else if (basket.getTotal() < basketProductOpt.get().getTotal()) {
                // If only some of the product is deleted, update the total in the basket
                Basket basketUpdated = basketProductOpt.get();
                basketUpdated.setTotal(basketUpdated.getTotal() - basket.getTotal());
                return updateBasket(basketUpdated);
            } else {
                // If an invalid request is made, return a BAD_REQUEST response
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            // If the product is not in the basket, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Calculate the total price of all products in the basket and return it along with the username
    public ResponseEntity<PriceOutput> getBasketTotalPrice(String username) {
        PriceOutput priceOutput = new PriceOutput();
        Double priceTotal = basketCalculateService.calculateBasketSum(readBasketAll(username).getBody());
        priceOutput.setPriceTotal(priceTotal);
        priceOutput.setUsername(username);
        return new ResponseEntity<>(priceOutput, HttpStatus.OK);
    }
}
