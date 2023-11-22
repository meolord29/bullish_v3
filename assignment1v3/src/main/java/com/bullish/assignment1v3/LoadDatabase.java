package com.bullish.assignment1v3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.AdminRepository;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.repository.ProductRepository;

@Configuration
public class LoadDatabase {
    
    @Bean
    CommandLineRunner initDatabase(AdminRepository adminRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        return args -> {
            adminRepository.save(new Admin("RootAdmin", "password"));
            productRepository.save(new Product("product1", 100f, 0.1f, 10));
            clientRepository.save(new Client("client1", "password123"));
        };
    }

}
