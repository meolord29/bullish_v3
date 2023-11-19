package com.bullish.assignment1v3.model.users;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "Admin")
@Table(name = "admin")
public class Admin extends AbstractUser{
    // Admin is the one that manages the software, person in charge of product management


    @Autowired
    public Admin(String username, String password) {
        super(username, password);
        //TODO Auto-generated constructor stub
    }
    
    @Autowired
    public Admin(){
        super();
    }
}
