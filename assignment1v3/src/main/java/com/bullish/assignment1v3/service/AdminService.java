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
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.AdminRepository;
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
    private BasketService basketService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientService clientService;

    // CRUD METHODS - Admin is able to do all of the CRUD methods within the system

    // PRODUCT CRUD SERVICES

    // Update a product
    @Override
    @Transactional
    public ResponseEntity<Product> updateProduct(Product productUpdated) {
        return productService.updateProduct(productUpdated);
    }

    // Read a product by name
    @Override
    public Optional<Product> readProduct(String productName) {
        return productService.readProduct(productName);
    }

    // Read all products
    @Override
    public ResponseEntity<List<Product>> readAllProducts() {
        return productService.readAllProducts();
    }

    // Add a new product
    @Override
    @Transactional
    public ResponseEntity<Product> addProduct(Product product) {
        return productService.addProduct(product);
    }

    // Delete a product
    @Override
    public ResponseEntity<Product> deleteProduct(Product product) {

        // Retrieve all baskets with the specified product
        List<Basket> basketsWithProduct = basketService.readAllBasketsByProductName(product);

        // Remove the product from people's baskets
        for (Basket basket : basketsWithProduct) {
            clientService.removeFromBasket(basket);
        }

        return productService.deleteProduct(product);
    }


    // ADMIN CRUD SERVICES

    // Read an admin by username
    @Override
    public Optional<Admin> readAdmin(String username) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        return adminOpt;
    }

    // Read all admins
    @Override
    public ResponseEntity<List<Admin>> readAllAdmins() {
        List<Admin> admins = adminRepository.findAll();

        if (admins != null && !admins.isEmpty()) {
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new admin
    @Override
    @Transactional
    public ResponseEntity<Admin> addAdmin(Admin admin) {
        Optional<Admin> adminOpt = readAdmin(admin.getUsername());

        if (adminOpt.isPresent()) {
            // Admin with the same username already exists
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            // Save the new admin
            adminRepository.save(admin);
            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        }
    }

    // Delete an admin
    @Override
    public ResponseEntity<Admin> deleteAdmin(Admin admin) {
        Optional<Admin> adminOpt = readAdmin(admin.getUsername());

        if (adminOpt.isPresent() && !"RootAdmin".equals(adminOpt.get().getUsername())) {
            // Delete the admin (except for the special "RootAdmin")
            adminRepository.delete(adminOpt.get());
            return new ResponseEntity<>(adminOpt.get(), HttpStatus.OK);
        } else {
            // Admin not found or trying to delete "RootAdmin"
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Update an admin
    @Override
    @Transactional
    public ResponseEntity<Admin> updateAdmin(Admin adminUpdated) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Optional<Admin> adminOpt = readAdmin(adminUpdated.getUsername());

        if (adminOpt.isPresent()){
            // Admin found, update the fields
            Admin admin = adminOpt.get();
            mapper.map(adminUpdated, admin);
            adminRepository.save(admin);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            // Admin not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // CLIENT CRUD SERVICES

    // Read a client by username
    @Override
    public Optional<Client> readClient(String username) {
        return clientService.readClient(username);
    }

    // Read all clients
    @Override
    public ResponseEntity<List<Client>> readAllClients() {
        return clientService.readAllClients();
    }
}
