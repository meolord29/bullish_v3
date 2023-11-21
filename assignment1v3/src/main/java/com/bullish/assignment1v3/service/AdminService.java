package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> updateProduct(String name, Product productUpdated) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Optional<Product> productOpt = readProduct(name);

        if (productOpt.isPresent()){
            Product product = productOpt.get();
            mapper.map(productUpdated, product);
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public Optional<Product> readProduct(String productName) {
        Optional<Product> productOpt = productRepository.findByName(productName);

        return productOpt;
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
    public ResponseEntity<Admin> readAdmin(String username) {

        Optional<Admin> adminOpt = adminRepository.findByUsername(username);

        if (adminOpt.isPresent()) {
            return new ResponseEntity<>(adminOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    public ResponseEntity<List<Admin>> readAllAdmins() {

        List<Admin> admins = adminRepository.findAll();

        if (admins != null && !admins.isEmpty()) {
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    public ResponseEntity<List<Product>> readAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products != null && !products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Client> readClient(String username) {

        Optional<Client> clientOpt = clientRepository.findByUsername(username);

        if (clientOpt.isPresent()) {
            return new ResponseEntity<>(clientOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Client>> readAllClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients != null && !clients.isEmpty()) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    

    

    
    
}
