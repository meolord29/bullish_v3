package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.ConfirmedPurchase;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.model.utility.PriceOutput;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.service.contracts.client.ClientAddableService;
import com.bullish.assignment1v3.service.contracts.client.ClientDeletableService;
import com.bullish.assignment1v3.service.contracts.client.ClientReadableService;
import com.bullish.assignment1v3.service.contracts.client.ClientUpdatableService;
import com.bullish.assignment1v3.service.contracts.client.ClientsReadableService;

import jakarta.transaction.Transactional;

@Service
public class ClientService 
implements ClientAddableService, ClientDeletableService, ClientUpdatableService, ClientReadableService, ClientsReadableService {

    // Repositories and services injected for database operations
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductService productService;

    @Autowired
	private BasketService basketService;

    @Autowired
    private ConfirmedPurchaseService confirmedPurchaseService;

    // CLIENT CRUD SERVICES

    // Update client information
    @Override
    @Transactional
    public ResponseEntity<Client> updateClient(Client clientUpdated) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        // Check if the client exists
        Optional<Client> clientOpt = readClient(clientUpdated.getUsername());

        if (clientOpt.isPresent()) {
            // If client exists, update its fields and save to the database
            Client client = clientOpt.get();
            mapper.map(clientUpdated, client);
            clientRepository.save(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            // If client not found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Read client information by username
    @Override
    public Optional<Client> readClient(String clientUsername) {
        Optional<Client> clientOpt = clientRepository.findByUsername(clientUsername);
        return clientOpt;
    }

    // Add a new client
    @Override
    @Transactional
    public ResponseEntity<Client> addClient(Client client) {
        Optional<Client> clientOpt = readClient(client.getUsername());

        if (clientOpt.isPresent()) {
            // If client with the same username already exists, return a BAD_REQUEST response
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            // Save the new client
            clientRepository.save(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        }
    }

    // Read all clients
    @Override
    public ResponseEntity<List<Client>> readAllClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients != null && !clients.isEmpty()) {
            // If clients found, return them with an OK status
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            // If no clients found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a client
    @Override
    public ResponseEntity<Client> deleteClient(Client client) {
        Optional<Client> clientOpt = readClient(client.getUsername());

        if (clientOpt.isPresent()) {
            // If client exists, delete it and return with an OK status
            clientRepository.delete(clientOpt.get());
            return new ResponseEntity<>(clientOpt.get(), HttpStatus.OK);
        } else {
            // If client not found, return a NOT_FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // All actions related to products

    // Read a product by name
    public Optional<Product> readProduct(String productName) {
        return productService.readProduct(productName);
    }
    
    // Read all products
    public ResponseEntity<List<Product>> readAllProducts() {
        return productService.readAllProducts();
    }

    // All actions related to the basket

    // Update basket (add or update products in the basket)
    public ResponseEntity<Basket> updateBasket(Basket basket) {
        return basketService.addToBasket(basket);
    }

    // Add a product to the basket
    public ResponseEntity<Basket> addToBasket(Basket basket) {
        return basketService.addToBasket(basket);
    }

    // Remove a product from the basket
    public ResponseEntity<Basket> removeFromBasket(Basket basket) {
        return basketService.removeFromBasket(basket);
    }

    // Get the details of the basket
    public ResponseEntity<List<Basket>> getBasket(String clientUsername) {
        return basketService.readBasketAll(clientUsername);
    }

    // Get the total price of the basket including discounts
    public ResponseEntity<PriceOutput> getBasketTotalPrice(String clientUsername) {
        return basketService.getBasketTotalPrice(clientUsername);
    }

    // All actions related to confirmed purchases

    // Add a purchase to the confirmed purchases
    public ResponseEntity<ConfirmedPurchase> addToConfirmedPurchase(ConfirmedPurchase confirmedPurchase) {
        return confirmedPurchaseService.addConfirmedPurchase(confirmedPurchase);
    }

    // Read all confirmed purchases for a client
    public ResponseEntity<List<ConfirmedPurchase>> ReadAllConfirmedPurchase(String username) {
        return confirmedPurchaseService.readAllConfirmedPurchase(username);
    }
}


