package com.bullish.assignment1v3.controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.bullish.assignment1v3.service.AdminService;
import com.bullish.assignment1v3.service.ClientService;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    // Admin related operations
    @GetMapping("/admins")
    ResponseEntity<List<Admin>> getAllAdmin(){
        List<Admin> admins = adminService.readAllAdmins();

        if (admins != null && !admins.isEmpty()) {
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/admins/{username}")
    ResponseEntity<Admin> getAdmin(@PathVariable String username) {
        Optional<Admin> admin = adminService.readAdmin(username);

        if (admin.isPresent()) {
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Client related operations
    @GetMapping("/clients")
    ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = adminService.readAllClients();

        if (clients != null && !clients.isEmpty()) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clients/{username}")
    ResponseEntity<Admin> getClient(@PathVariable String username) {
        Optional<Admin> admin = adminService.readAdmin(username);

        if (admin.isPresent()) {
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }


    // Product related operations
    @GetMapping("/products")
    ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = adminService.readAllProducts();

        if (products != null && !products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
