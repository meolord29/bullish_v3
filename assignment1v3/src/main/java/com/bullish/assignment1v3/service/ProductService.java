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
implements ProductAddableService, ProductReadableService, ProductUpdatableService, ProductDeletableService, ProductsReadableService{
    
    @Autowired
    private ProductRepository productRepository;

    // PRODUCT CRUD SERVICES
    @Override
    @Transactional
    public ResponseEntity<Product> updateProduct(Product productUpdated) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Optional<Product> productOpt = readProduct(productUpdated.getName());

        if (productOpt.isPresent()){
            Product product = productOpt.get();
            mapper.map(productUpdated, product);
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<Product> readProduct(String productName) {
        Optional<Product> productOpt = productRepository.findByName(productName);
        return productOpt;
    }

    @Override
    public ResponseEntity<List<Product>> readAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products != null && !products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Product> addProduct(Product product) {
        Optional<Product> productOpt = readProduct(product.getName());

        if (productOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Product> deleteProduct(Product product) {
        Optional<Product> productOpt = readProduct(product.getName());

        if (productOpt.isPresent()) {
            productRepository.delete(productOpt.get());
            return new ResponseEntity<>(productOpt.get(), HttpStatus.OK);
            
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
