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

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.ConfirmedPurchase;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.model.utility.PriceOutput;
import com.bullish.assignment1v3.service.ClientService;

@RestController
@RequestMapping("/client_access")
public class ClientController {

    // Autowired ClientService for business logic operations
    @Autowired
    private ClientService clientService;

    // Constructor to inject dependencies
    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Client related operations

    // Get a specific client by username
    @GetMapping("/clients/{username}")
    ResponseEntity<Client> getClient(@PathVariable String username){
        Optional<Client> clientOpt = clientService.readClient(username);

        if (clientOpt.isPresent()) {
            // If client found, return with OK status
            return new ResponseEntity<>(clientOpt.get(), HttpStatus.OK);
        } else {
            // If client not found, return with NOT_FOUND status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new client
    @PostMapping("/clients/{client}")
    ResponseEntity<Client> createClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    // Update an existing client
    @PutMapping("/clients/{client}")
    ResponseEntity<Client> updateClient(@RequestBody Client client){
        return clientService.updateClient(client);
    }

    // Remove a client
    @DeleteMapping("/clients/{client}")
    ResponseEntity<Client> removeClient(@RequestBody Client client){
        return clientService.deleteClient(client);
    }

    // Product related operations

    // Get all products
    @GetMapping("/products")
    ResponseEntity<List<Product>> getAllProducts(){
        return clientService.readAllProducts();
    }

    // Get a specific product by name
    @GetMapping("/products/{name}")
    ResponseEntity<Product> getProduct(@PathVariable String name){
        Optional<Product> productOpt = clientService.readProduct(name);

        if (productOpt.isPresent()) {
            // If product found, return with OK status
            return new ResponseEntity<>(productOpt.get(), HttpStatus.OK);
        } else {
            // If product not found, return with NOT_FOUND status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Basket related operations

    // Get all baskets for a specific client
    @GetMapping("/basket/{username}/all")
    ResponseEntity<List<Basket>> getBasket(@PathVariable String username){
        return clientService.getBasket(username);
    }

    // Add a new item to the basket
    @PostMapping("/basket")
    ResponseEntity<Basket> addToBasket(@RequestBody Basket basket){
        return clientService.addToBasket(basket);
    }

    // Update the basket
    @PutMapping("/basket")
    ResponseEntity<Basket> updateBasket(@RequestBody Basket basket){
        return clientService.updateBasket(basket);
    }

    // Remove an item from the basket
    @DeleteMapping("/basket")
    ResponseEntity<Basket> removeFromBasket(@RequestBody Basket basket){
        return clientService.removeFromBasket(basket);
    }

    // Get the total price of the basket
    @GetMapping("/basket/{username}/priceTotal")
    ResponseEntity<PriceOutput> getBasketTotalPrice(@PathVariable String username){
        return clientService.getBasketTotalPrice(username);
    }

    // ConfirmedPurchase related operations

    // Add a confirmed purchase
    @PostMapping("/confirmedPurchase/{username}")
    ResponseEntity<ConfirmedPurchase> addToConfirmedPurchase(@RequestBody ConfirmedPurchase confirmedPurchase){
        return clientService.addToConfirmedPurchase(confirmedPurchase);
    }

    // Get all confirmed purchases for a specific client
    @GetMapping("/confirmedPurchase/{username}/all")
    ResponseEntity<List<ConfirmedPurchase>> ReadAllConfirmedPurchase(@PathVariable String username){
        return clientService.ReadAllConfirmedPurchase(username);
    }
}
