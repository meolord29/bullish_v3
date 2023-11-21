package com.bullish.assignment1v3.controller;

import java.net.http.HttpResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
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
    ResponseEntity<List<Admin>> getAllAdmins(){
        return adminService.readAllAdmins();
    }

    @GetMapping("/admins/{name}")
    ResponseEntity<Admin> getAdmin(@PathVariable String username){
        Optional<Admin> productOpt = adminService.readAdmin(username);

        if (productOpt.isPresent()) {
            return new ResponseEntity<>(productOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admins/{admin}")
    ResponseEntity<Admin> createadmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    @PutMapping("/admins/{admin}")
    ResponseEntity<Admin> updateAdmin(@RequestBody Admin updatedAdmin){
        return adminService.updateAdmin(updatedAdmin);
    }
    @DeleteMapping("/admins/{admin}")
    ResponseEntity<Admin> removeAdmin(@RequestBody Admin admin){
        return adminService.deleteAdmin(admin);
    }



    // Client related operations
    @GetMapping("/clients")
    ResponseEntity<List<Client>> getAllClients(){
        return adminService.readAllClients();
    }

    @GetMapping("/clients/{name}")
    ResponseEntity<Client> getClient(@PathVariable String username){
        Optional<Client> clientOPt = adminService.readClient(username);

        if (clientOPt.isPresent()) {
            return new ResponseEntity<>(clientOPt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //@PostMapping("/clients/{client}}")
    //ResponseEntity<Client> createClient(@RequestBody Client client){
     //   return adminService.addClient(client);
    //}

    //@PutMapping("/clients/{client}}")
    //ResponseEntity<Client> updateClient(@RequestBody Client client){
     //   return adminService.updateClient(client);
    //}


    //@DeleteMapping("/clients/{client}}")
    //ResponseEntity<Client> removeClient(@RequestBody Client client){
     //   return adminService.deleteClient(client);
    //}


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
        return adminService.updateProduct(product);
    }
    @DeleteMapping("/products/{product}")
    ResponseEntity<Product> removeProduct(@RequestBody Product product){
        return adminService.deleteProduct(product);
    }

}
