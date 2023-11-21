package com.bullish.assignment1v3.service.contracts.admin;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminDeletableService {
    public ResponseEntity<Admin> deleteAdmin(Admin admin);
}
