package com.bullish.assignment1v3.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
abstract class AbstractUser {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name = "password")
    private String password;

    public AbstractUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public AbstractUser(){
        
    }

}
