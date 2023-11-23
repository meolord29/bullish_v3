package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.repository.ProductRepository;
import com.bullish.assignment1v3.service.contracts.product.ProductAddableService;
import com.bullish.assignment1v3.service.contracts.product.ProductDeletableService;
import com.bullish.assignment1v3.service.contracts.product.ProductReadableService;
import com.bullish.assignment1v3.service.contracts.product.ProductUpdatableService;
import com.bullish.assignment1v3.service.contracts.product.ProductsReadableService;

import jakarta.transaction.Transactional;
@Service
public class ProductService 
        implements ProductAddableService, ProductReadableService, ProductUpdatableService, ProductDeletableService, ProductsReadableService {
    
    // Repository for database operations
    @Autowired
    private ProductRepository productRepository;

    // PRODUCT CRUD SERVICES

    // Update product information
    @Override
    @Transactional
    public ResponseEntity<Product> updateProduct(Product productUpdated) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        // Check if the product exists
        Optional<Product> productOpt = readProduct(productUpdated.getName());

        if (productOpt.isPresent()){
            // If the product exists, update its fields and save to the database
            Product product = productOpt.get();
            mapper.map(productUpdated, product);
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            // If the product not found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Read product information by name
    @Override
    public Optional<Product> readProduct(String productName) {
        Optional<Product> productOpt = productRepository.findByName(productName);
        return productOpt;
    }

    // Read all products
    @Override
    public ResponseEntity<List<Product>> readAllProducts() {
        // Retrieve all products
        List<Product> products = productRepository.findAll();

        if (products != null && !products.isEmpty()) {
            // If products found, return them with an OK status
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            // If no products found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new product
    @Override
    @Transactional
    public ResponseEntity<Product> addProduct(Product product) {
        // Check if a product with the same name already exists
        Optional<Product> productOpt = readProduct(product.getName());

        if (productOpt.isPresent()) {
            // If the product already exists, return a BAD_REQUEST response
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            // Save the new product
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }
    }

    // Delete a product
    @Override
    public ResponseEntity<Product> deleteProduct(Product product) {
        // Check if the product exists
        Optional<Product> productOpt = readProduct(product.getName());

        if (productOpt.isPresent()) {
            // If the product exists, delete it and return with an OK status
            productRepository.delete(productOpt.get());
            return new ResponseEntity<>(productOpt.get(), HttpStatus.OK);
            
        } else {
            // If the product not found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
