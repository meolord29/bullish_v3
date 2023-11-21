package com.bullish.assignment1v3.service.contracts;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminReadable {

    public ResponseEntity<Admin> readAdmin(String username);

}
