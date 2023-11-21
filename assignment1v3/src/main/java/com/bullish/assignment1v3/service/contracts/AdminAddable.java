package com.bullish.assignment1v3.service.contracts;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminAddable {
    public ResponseEntity<Admin> addAdmin(Admin admin);
}
