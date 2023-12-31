package com.bullish.assignment1v3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.ConfirmedPurchase;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.AdminRepository;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.repository.ProductRepository;

@Configuration
public class LoadDatabase {
    
    @Bean
    CommandLineRunner initDatabase(
        AdminRepository adminRepository, 
        ProductRepository productRepository, 
        ClientRepository clientRepository, 
        BasketRepository basketRepository, 
        ConfirmedPurchaseRepository confirmedPurchaseRepository) 
        {
        return args -> {
            adminRepository.save(new Admin("RootAdmin", "password"));
            productRepository.save(new Product("product1", 100d, 0.1d, 10));
            clientRepository.save(new Client("client1", "password123"));
            basketRepository.save(new Basket("client1", "product1", 10));
            basketRepository.save(new Basket("client1", "product2", 20));

            
            productRepository.save(new Product("product7", 100d, 0.1d, 10));
            productRepository.save(new Product("product8", 500d, 0.1d, 10));
            clientRepository.save(new Client("client8", "password123"));
            basketRepository.save(new Basket("client8", "product7", 2));
            basketRepository.save(new Basket("client8", "product8", 2));

            basketRepository.save(new Basket("client44", "product8", 2));
            confirmedPurchaseRepository.save(new ConfirmedPurchase("client44", "product90", 2));
        };
    }
}
