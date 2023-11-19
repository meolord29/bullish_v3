package com.bullish.assignment1v3.initDatabaseTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.repository.AdminRepository;
import com.bullish.assignment1v3.service.AdminService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class checkRootAdminExistWhenInitDatabaseTests {
    
    @Autowired 
    private AdminRepository adminRepository;

    @Test   
    void injectedAdminIntoAdminRepositoryIsNotNull(){

        // Assert
        // Check that admin database is not empty\
        assertThat(adminRepository).isNotNull(); 
            

    }

}
