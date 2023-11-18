package com.bullish.assignment1v3.model.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends AbstractUser{
    // Client is the person who is interacting with the front end part of the software
    // Client is also the user that is in charge of their own Basket

    
}
