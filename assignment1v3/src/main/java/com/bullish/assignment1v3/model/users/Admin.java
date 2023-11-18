package com.bullish.assignment1v3.model.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends AbstractUser{
    // Admin is the one that manages the software, person in charge of product management

}
