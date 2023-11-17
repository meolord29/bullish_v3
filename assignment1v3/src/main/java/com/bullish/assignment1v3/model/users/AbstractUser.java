package com.bullish.assignment1v3.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
abstract class AbstractUser {

    @Column(name="username")
    public String username;

    @Column(name = "password")
    public String password;

}
