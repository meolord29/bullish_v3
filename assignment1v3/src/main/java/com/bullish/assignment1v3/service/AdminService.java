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
import com.bullish.assignment1v3.service.contracts.admin.AdminAddableService;
import com.bullish.assignment1v3.service.contracts.admin.AdminDeletableService;
import com.bullish.assignment1v3.service.contracts.admin.AdminReadableService;
import com.bullish.assignment1v3.service.contracts.admin.AdminUpdatableService;
import com.bullish.assignment1v3.service.contracts.admin.AdminsReadableService;
import com.bullish.assignment1v3.service.contracts.client.ClientReadableService;
import com.bullish.assignment1v3.service.contracts.client.ClientsReadableService;
import com.bullish.assignment1v3.service.contracts.product.ProductAddableService;
import com.bullish.assignment1v3.service.contracts.product.ProductDeletableService;
import com.bullish.assignment1v3.service.contracts.product.ProductReadableService;
import com.bullish.assignment1v3.service.contracts.product.ProductUpdatableService;
import com.bullish.assignment1v3.service.contracts.product.ProductsReadableService;

import jakarta.transaction.Transactional;



@Service
public class AdminService implements 
ProductAddableService, ProductReadableService, ProductUpdatableService, ProductDeletableService, ProductsReadableService,
AdminReadableService, AdminAddableService, AdminUpdatableService, AdminDeletableService, AdminsReadableService,
ClientReadableService, ClientsReadableService
{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientService clientService;

    // CRUD METHODS - Admin is able to do all of the CRUD methods within the system

    // PRODUCT CRUD SERVICES
    @Override
    @Transactional
    public ResponseEntity<Product> updateProduct(Product productUpdated) {
        return productService.updateProduct(productUpdated);
    }

    @Override
    public Optional<Product> readProduct(String productName) {
        Optional<Product> productOpt = productRepository.findByName(productName);
        return productOpt;
    }

    @Override
    public ResponseEntity<List<Product>> readAllProducts() {
        return productService.readAllProducts();
    }

    @Override
    @Transactional
    public ResponseEntity<Product> addProduct(Product product) {
        return productService.addProduct(product);
    }

    @Override
    public ResponseEntity<Product> deleteProduct(Product product) {
        return productService.deleteProduct(product);
    }


    // ADMIN CRUD SERVICES
    @Override
    public Optional<Admin> readAdmin(String username) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        return adminOpt;
    }

    @Override
    public ResponseEntity<List<Admin>> readAllAdmins() {
        List<Admin> admins = adminRepository.findAll();

        if (admins != null && !admins.isEmpty()) {
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Admin> addAdmin(Admin admin) {
        Optional<Admin> adminOpt = readAdmin(admin.getUsername());

        if (adminOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            adminRepository.save(admin);
            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Admin> deleteAdmin(Admin admin) {
        Optional<Admin> adminOpt = readAdmin(admin.getUsername());

        if (adminOpt.isPresent() && adminOpt.get().getUsername() != "RootAdmin") {
            adminRepository.delete(adminOpt.get());
            return new ResponseEntity<>(adminOpt.get(), HttpStatus.OK);
            
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Admin> updateAdmin(Admin adminUpdated) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Optional<Admin> adminOpt = readAdmin(adminUpdated.getUsername());

        if (adminOpt.isPresent()){
            Admin admin = adminOpt.get();
            mapper.map(adminUpdated, admin);
            adminRepository.save(admin);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<Client> readClient(String username) {

        return clientService.readClient(username);

    }

    @Override
    public ResponseEntity<List<Client>> readAllClients() {
        return clientService.readAllClients();
    }

    



    

    

    
    
}
