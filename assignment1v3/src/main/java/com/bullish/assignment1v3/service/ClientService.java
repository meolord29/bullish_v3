package com.bullish.assignment1v3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.repository.ProductRepository;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.AddableConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.ReadableConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.basket.AddableToBasketService;
import com.bullish.assignment1v3.service.contracts.basket.BasketReadableService;
import com.bullish.assignment1v3.service.contracts.basket.RemovableFromBasketService;
import com.bullish.assignment1v3.service.contracts.basket.UpdatableBasketService;
import com.bullish.assignment1v3.service.contracts.client.ClientAddableService;
import com.bullish.assignment1v3.service.contracts.client.ClientDeletableService;
import com.bullish.assignment1v3.service.contracts.client.ClientReadableService;
import com.bullish.assignment1v3.service.contracts.client.ClientUpdatableService;
import com.bullish.assignment1v3.service.contracts.product.ProductReadableService;
import com.bullish.assignment1v3.service.contracts.product.ProductsReadableService;

import jakarta.transaction.Transactional;

@Service
public class ClientService 
implements ProductReadableService, 
BasketReadableService, RemovableFromBasketService, AddableToBasketService, UpdatableBasketService, 
AddableConfirmedPurchaseService, ReadableConfirmedPurchaseService,
ClientAddableService, ClientDeletableService, ClientUpdatableService, ClientReadableService,
ProductsReadableService
{

    // Client can only view what products exist -> R of CRUD within the Library of PRODUCTS
    // Client can fully manage their Basket -> All CRUD Functionalities within the BASKET
    // Client can buy and review products that are in the basket -> CR of CRUD within the CONFIRMED PURCHASE of the basket

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
	private ProductRepository productRepository;

    @Autowired
	private BasketRepository basketRepository;

    @Autowired 
    private ConfirmedPurchaseRepository confirmedPurchaseRepository;


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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            clientRepository.save(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
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
    @Override
    public Optional<Product> readProduct(String productName) {
        Optional<Product> productOpt = productRepository.findByName(productName);
        return productOpt;
    }

    @Override
    public ResponseEntity<List<Product>> readAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products != null && !products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    

    @Override
    public void updateBasket(BasketRepository basketRepository) {// CR_U_D for Basket Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBasket'");
    }


    @Override
    public void addToBasket(Product product) {// _C_RUD for Basket Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToBasket'");
    }

    @Override
    public void removeFromBasket(BasketRepository basketRepository) {// CRU_D_ for Basket Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromBasket'");
    }


    @Override
    public Basket readBasket(BasketRepository basketRepository) { // C_R_UD for Basket Table
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readBasket'");
    }


    @Override
    public ConfirmedPurchaseRepository readConfirmedPurchase(ConfirmedPurchaseRepository confirmedPurchaseRepository) { 
        // C_R_UD for Basket Table

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readConfirmedPurchase'");
    }


    @Override
    public void addConfirmedPurchase(ConfirmedPurchaseRepository confirmedPurchaseRepository) {
        // _C_RUD for Basket Table
        // First: Check if Product is a non zero count in the products table, else abort the method
        // Second: method checks if product exists within the basket, else abort the method
        // Third: method requests Admin Service to delete product from the product Table with specified amount
        // Forth: method calls the updateBasket to update the counter of specific product counter
        // Fifth: method adds product to confirmedPurchase table -> update method with +n


        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addConfirmedPurchase'");
    }

    

    
}
