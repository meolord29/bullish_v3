package com.bullish.assignment1v3.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bullish.assignment1v3.controller.adminExceptions.AdminNotFoundException;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.AdminRepository;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.repository.ProductRepository;

@RestController
public class AdminController {
    
    private final AdminRepository adminRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    AdminController(AdminRepository adminRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.adminRepository = adminRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }


    // Admin related operations
    @GetMapping("/admins")
    List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    Admin one(@PathVariable Long id) {
        
        return adminRepository.findById(id)
        .orElseThrow(() -> new AdminNotFoundException(id));
    }

    // Client related operations
    @GetMapping("/clients")
    List<Client> getAllClients(){
        return clientRepository.findAll();
    }


    // Product related operations
    @GetMapping("/products")
    List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
