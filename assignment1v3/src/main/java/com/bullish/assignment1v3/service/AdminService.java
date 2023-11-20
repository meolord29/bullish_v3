package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.AdminRepository;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.repository.ProductRepository;
import com.bullish.assignment1v3.service.contracts.AdminReadable;
import com.bullish.assignment1v3.service.contracts.ProductAddableService;
import com.bullish.assignment1v3.service.contracts.ProductReadableService;
import com.bullish.assignment1v3.service.contracts.ProductRemovableService;
import com.bullish.assignment1v3.service.contracts.ProductUpdatableService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;



@Service
public class AdminService implements ProductAddableService, ProductReadableService, ProductUpdatableService, ProductRemovableService,
AdminReadable{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    // CRUD METHODS - Admin is able to do all of the CRUD methods within the system

    @Override
    @Transactional
    public void removeProduct(ProductRepository productRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeProduct'");
    }

    @Override
    @Transactional
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
        else{
            // NEED TO FILL THIS IN LATER
        }

    }

    @Override
    public Optional<Product> readProduct(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productRepository.save(product);
        
    }

    @Override
    public Optional<Admin> readAdmin(String username) {
        return adminRepository.findByUsername(username);
    }

    public List<Admin> readAllAdmins() {
        return adminRepository.findAll();
    }

    public List<Product> readAllProducts() {
        return productRepository.findAll();
    }

    public List<Client> readAllClients() {
        return clientRepository.findAll();
    }



    

    

    
    
}
