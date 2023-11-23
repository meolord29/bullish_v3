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
import com.bullish.assignment1v3.model.store.PriceOutput;
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

    @GetMapping("/basket/{username}/priceTotal")
    ResponseEntity<PriceOutput> getBasketTotalPrice(@PathVariable String username){
        return clientService.getBasketTotalPrice(username);
    }

    // All to do with ConfirmedPurchase

    @GetMapping("/confirmedPurchase/client8/all")
    ResponseEntity<ConfirmedPurchase> addToConfirmedPurchase(@RequestBody ConfirmedPurchase confirmedPurchase){
        return clientService.addToConfirmedPurchase(confirmedPurchase);
    }

}
