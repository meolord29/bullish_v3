package com.bullish.assignment1v3.model.users;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends AbstractUser{

    @Autowired
    public Admin(String username, String password) {
        super(username, password);
        //TODO Auto-generated constructor stub
    }
    // Admin is the one that manages the software, person in charge of product management

}
