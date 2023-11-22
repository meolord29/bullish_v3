package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.PriceOutput;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.service.contracts.client.ClientAddableService;
import com.bullish.assignment1v3.service.contracts.client.ClientDeletableService;
import com.bullish.assignment1v3.service.contracts.client.ClientReadableService;
import com.bullish.assignment1v3.service.contracts.client.ClientUpdatableService;
import com.bullish.assignment1v3.service.contracts.client.ClientsReadableService;

import jakarta.transaction.Transactional;

@Service
public class ClientService 
implements ClientAddableService, ClientDeletableService, ClientUpdatableService, ClientReadableService, ClientsReadableService

{

    // Client can only view what products exist -> R of CRUD within the Library of PRODUCTS
    // Client can fully manage their Basket -> All CRUD Functionalities within the BASKET
    // Client can buy and review products that are in the basket -> CR of CRUD within the CONFIRMED PURCHASE of the basket

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductService productService;

    @Autowired
	private BasketService basketService;


    // CLIENT CRUD SERVICES
    @Override
    @Transactional
    public ResponseEntity<Client> updateClient(Client clientUpdated) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Optional<Client> clientOpt = readClient(clientUpdated.getUsername());

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            mapper.map(clientUpdated, client);
            clientRepository.save(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<Client> readClient(String clientUsername) {
        Optional<Client> clientOpt = clientRepository.findByUsername(clientUsername);
        return clientOpt;
    }

    @Override
    @Transactional
    public ResponseEntity<Client> addClient(Client client) {
        Optional<Client> clientOpt = readClient(client.getUsername());

        if (clientOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            clientRepository.save(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<List<Client>> readAllClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients != null && !clients.isEmpty()) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Client> deleteClient(Client client) {
        Optional<Client> clientOpt = readClient(client.getUsername());

        if (clientOpt.isPresent()) {
            clientRepository.delete(clientOpt.get());
            return new ResponseEntity<>(clientOpt.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // All to do with products
    public Optional<Product> readProduct(String productName) {
        return productService.readProduct(productName);
    }
    
    public ResponseEntity<List<Product>> readAllProducts() {
        return productService.readAllProducts();
    }

    
    // All to do with basket

    // CR_U_D for Basket Table
    public ResponseEntity<Basket> updateBasket(Basket basket) {
        return basketService.addToBasket(basket);
    }

    // _C_RUD for Basket Table
    public ResponseEntity<Basket> addToBasket(Basket basket) {// _C_RUD for Basket Table
        return basketService.addToBasket(basket);
    }

    // CRU_D_ for Basket Table
    public ResponseEntity<Basket> removeFromBasket(Basket basket) {// CRU_D_ for Basket Table
        return basketService.removeFromBasket(basket);
    }

    // C_R_UD for Basket Table
    public ResponseEntity<List<Basket>> getBasket(String clientUsername) { // C_R_UD for Basket Table
        return basketService.readBasketAll(clientUsername);
    }
    // getting price for the basket total including the discounts
    public ResponseEntity<PriceOutput> getTotalPrice(String clientUsername){
        return basketService.getTotalPrice(clientUsername);
    }

    // All actions to do with ConfirmedPurchases



    

    
}
