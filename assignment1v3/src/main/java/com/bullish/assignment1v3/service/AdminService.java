package com.bullish.assignment1v3.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.repository.AdminRepository;
import com.bullish.assignment1v3.repository.ProductRepository;
import com.bullish.assignment1v3.service.contracts.AdminReadable;
import com.bullish.assignment1v3.service.contracts.ProductAddableService;
import com.bullish.assignment1v3.service.contracts.ProductReadableService;
import com.bullish.assignment1v3.service.contracts.ProductRemovableService;
import com.bullish.assignment1v3.service.contracts.ProductUpdatableService;



@Service
public class AdminService implements ProductAddableService, ProductReadableService, ProductUpdatableService, ProductRemovableService,
AdminReadable{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProductRepository productRepository;

    // CRUD METHODS - Admin is able to do all of the CRUD methods within the system

    @Override
    public void removeProduct(ProductRepository productRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeProduct'");
    }

    @Override
    public void updateProduct(String name, Product productUpdated) {

        ModelMapper mapper = new ModelMapper();
        // this will tell ModelMapper to ignore null fields when mapping the source (newData) to the destination (user)
        mapper.getConfiguration().setSkipNullEnabled(true);
        
        Optional<Product> productOpt = readProduct(name);

        if (productOpt.isPresent()){
            Product product = productOpt.get();
            mapper.map(productUpdated, product);
            productRepository.save(product);
        }

    }

    @Override
    public Optional<Product> readProduct(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Admin> readAdmin(String username) {
        // TODO Auto-generated method stub
        return adminRepository.findByUsername(username);
    }



    

    

    
    
}
