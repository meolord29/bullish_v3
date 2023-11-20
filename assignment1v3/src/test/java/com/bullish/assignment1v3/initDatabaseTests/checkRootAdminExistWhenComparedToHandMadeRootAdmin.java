package com.bullish.assignment1v3.initDatabaseTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.repository.AdminRepository;
import com.bullish.assignment1v3.service.AdminService;

@SpringBootTest
public class checkRootAdminExistWhenComparedToHandMadeRootAdmin {
    
    @Autowired
    private AdminService adminService;

    @Test
    void injectedAdminRetrievableFromAdminRepository(){

        // Arrange
        String username = "RootAdmin";
        String password = "password";
        Admin rootAdmin = new Admin(username, password);

        // Act
        Admin retrievedAdmin = adminService.readAdmin(username).get();


        // Assert
            // Check that the root admin exists in the database
        assertThat(rootAdmin.getUsername()).isEqualTo(retrievedAdmin.getUsername());
        assertThat(rootAdmin.getPassword()).isEqualTo(retrievedAdmin.getPassword());
    }

}
