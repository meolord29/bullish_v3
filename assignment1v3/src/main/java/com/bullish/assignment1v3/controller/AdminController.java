package com.bullish.assignment1v3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.service.AdminService;

@RestController
@RequestMapping("/admin_access")
public class AdminController {

    // Autowired AdminService for business logic operations
    @Autowired
    private AdminService adminService;

    // Constructor to inject dependencies
    AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin related operations

    // Get all admins
    @GetMapping("/admins")
    ResponseEntity<List<Admin>> getAllAdmins(){
        return adminService.readAllAdmins();
    }

    // Get a specific admin by username
    @GetMapping("/admins/{username}")
    ResponseEntity<Admin> getAdmin(@PathVariable String username){
        Optional<Admin> adminOpt = adminService.readAdmin(username);

        if (adminOpt.isPresent()) {
            // If admin found, return with OK status
            return new ResponseEntity<>(adminOpt.get(), HttpStatus.OK);
        } else {
            // If admin not found, return with NOT_FOUND status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new admin
    @PostMapping("/admins/{admin}")
    ResponseEntity<Admin> createAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    // Update an existing admin
    @PutMapping("/admins/{admin}")
    ResponseEntity<Admin> updateAdmin(@RequestBody Admin updatedAdmin){
        return adminService.updateAdmin(updatedAdmin);
    }

    // Remove an admin
    @DeleteMapping("/admins/{admin}")
    ResponseEntity<Admin> removeAdmin(@RequestBody Admin admin){
        return adminService.deleteAdmin(admin);
    }

    // Client related operations

    // Get all clients
    @GetMapping("/clients")
    ResponseEntity<List<Client>> getAllClients(){
        return adminService.readAllClients();
    }

    // Get a specific client by username
    @GetMapping("/clients/{name}")
    ResponseEntity<Client> getClient(@PathVariable String username){
        Optional<Client> clientOpt = adminService.readClient(username);

        if (clientOpt.isPresent()) {
            // If client found, return with OK status
            return new ResponseEntity<>(clientOpt.get(), HttpStatus.OK);
        } else {
            // If client not found, return with BAD_REQUEST status (consider using NOT_FOUND)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Commented out as it's currently not implemented
    //@PostMapping("/clients/{client}}")
    //ResponseEntity<Client> createClient(@RequestBody Client client){
     //   return adminService.addClient(client);
    //}

    // Commented out as it's currently not implemented
    //@PutMapping("/clients/{client}}")
    //ResponseEntity<Client> updateClient(@RequestBody Client client){
     //   return adminService.updateClient(client);
    //}

    // Commented out as it's currently not implemented
    //@DeleteMapping("/clients/{client}}")
    //ResponseEntity<Client> removeClient(@RequestBody Client client){
     //   return adminService.deleteClient(client);
    //}

    // Product related operations

    // Get all products
    @GetMapping("/products")
    ResponseEntity<List<Product>> getAllProducts(){
        return adminService.readAllProducts();
    }

    // Get a specific product by name
    @GetMapping("/products/{name}")
    ResponseEntity<Product> getProduct(@PathVariable String name){
        Optional<Product> productOpt = adminService.readProduct(name);

        if (productOpt.isPresent()) {
            // If product found, return with OK status
            return new ResponseEntity<>(productOpt.get(), HttpStatus.OK);
        } else {
            // If product not found, return with NOT_FOUND status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new product
    @PostMapping("/products/{product}")
    ResponseEntity<Product> createProduct(@RequestBody Product product){
        return adminService.addProduct(product);
    }

    // Update an existing product
    @PutMapping("/products/{product}")
    ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return adminService.updateProduct(product);
    }

    // Remove a product
    @DeleteMapping("/products/{product}")
    ResponseEntity<Product> removeProduct(@RequestBody Product product){
        return adminService.deleteProduct(product);
    }
}
