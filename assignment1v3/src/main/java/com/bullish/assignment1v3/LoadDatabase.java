package com.bullish.assignment1v3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.repository.AdminRepository;

@Configuration
public class LoadDatabase {
    
    @Bean
    CommandLineRunner initDatabase(AdminRepository adminRepository) {
        return args -> {
            adminRepository.save(new Admin("RootAdmin", "password"));
        };
    }

}
