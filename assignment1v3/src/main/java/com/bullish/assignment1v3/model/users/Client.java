package com.bullish.assignment1v3.model.users;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends AbstractUser{

    @Autowired
    public Client(String username, String password) {
        super(username, password);
        //TODO Auto-generated constructor stub
    }
    // Client is the person who is interacting with the front end part of the software
    // Client is also the user that is in charge of their own Basket
    
}
