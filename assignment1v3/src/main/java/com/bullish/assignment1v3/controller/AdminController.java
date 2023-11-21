package com.bullish.assignment1v3.controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.service.AdminService;

import jakarta.persistence.EntityExistsException;

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
        return adminService.readAllAdmins();
    }

    @GetMapping("/admins/{username}")
    ResponseEntity<Admin> getAdmin(@PathVariable String username) {
        return adminService.readAdmin(username);
    }

    // Client related operations
    @GetMapping("/clients")
    ResponseEntity<List<Client>> getAllClients(){
        return adminService.readAllClients();
    }

    @GetMapping("/clients/{username}")
    ResponseEntity<Client> getClient(@PathVariable String username) {
        return adminService.readClient(username);
    }


    // Product related operations
    @GetMapping("/products")
    ResponseEntity<List<Product>> getAllProducts(){
        return adminService.readAllProducts();
    }

    @GetMapping("/products/{name}")
    ResponseEntity<Product> getProduct(@PathVariable String name){
        Optional<Product> productOpt = adminService.readProduct(name);

        if (productOpt.isPresent()) {
            return new ResponseEntity<>(productOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products/{product}")
    ResponseEntity<Product> createProduct(@RequestBody Product product){
        return adminService.addProduct(product);
    }

    @PutMapping("/products/{product}")
    ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return adminService.updateProduct(product.getName(), product);
    }


}
