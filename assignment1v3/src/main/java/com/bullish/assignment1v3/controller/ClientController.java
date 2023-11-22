package com.bullish.assignment1v3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.service.ClientService;

@RestController
@RequestMapping("/client_access")
public class ClientController {

    @Autowired
    private ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // All to do with clients
    @GetMapping("/clients/{username}")
    ResponseEntity<Client> getClient(@PathVariable String username){
        Optional<Client> clientOPt = clientService.readClient(username);

        if (clientOPt.isPresent()) {
            return new ResponseEntity<>(clientOPt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/clients/{client}")
    ResponseEntity<Client> createClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @PutMapping("/clients/{client}")
    ResponseEntity<Client> updateClient(@RequestBody Client client){
        return clientService.updateClient(client);
    }


    @DeleteMapping("/clients/{client}")
    ResponseEntity<Client> removeClient(@RequestBody Client client){
        return clientService.deleteClient(client);
    }
    

    @GetMapping("/products")
    ResponseEntity<List<Product>> getAllProducts(){
        return clientService.readAllProducts();
    }

    @GetMapping("/products/{name}")
    ResponseEntity<Product> getProduct(@PathVariable String name){
        Optional<Product> productOpt = clientService.readProduct(name);

        if (productOpt.isPresent()) {
            return new ResponseEntity<>(productOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // All to do with basket
    @GetMapping("/basket/{username}/all")
    ResponseEntity<List<Basket>> getBasket(@PathVariable String username){
        return clientService.getBasket(username);
    }

    @PostMapping("/basket")
    ResponseEntity<Basket> addToBasket(@RequestBody Basket basket){
        return clientService.addToBasket(basket);
    }

    @PutMapping("/basket")
    ResponseEntity<Basket> updateBasket(@RequestBody Basket basket){
        return clientService.updateBasket(basket);
    }

    @DeleteMapping("/basket")
    ResponseEntity<Basket> removeFromBasket(@RequestBody Basket basket){
        return clientService.removeFromBasket(basket);
    }


//     // Helper methods to map values from the requestMap to Client and Product
// private Client mapToClient(Map<String, Object> requestMap) {
//     Client client = new Client();
//     if (requestMap.containsKey("client")) {
//         Map<String, Object> clientMap = (Map<String, Object>) requestMap.get("client");
//         // Assuming Client has setters for the properties
//         client.setUsername((String) clientMap.get("username"));
//         client.setPassword((String) clientMap.get("password"));
//         // Add other properties as needed
//     }
//     return client;
// }

// private Product mapToProduct(Map<String, Object> requestMap) {
//     Product product = new Product();
//     if (requestMap.containsKey("product")) {
//         Map<String, Object> productMap = (Map<String, Object>) requestMap.get("product");
//         // Assuming Product has setters for the properties
//         product.setName((String) productMap.get("name"));
//         Integer number = (Integer) productMap.get("total");
        
//         product.setTotal(number);
//     }
//     return product;
// }

}
