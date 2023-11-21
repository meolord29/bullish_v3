package com.bullish.assignment1v3.service.contracts.admin;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminUpdatableService {

    public ResponseEntity<Admin> updateAdmin(Admin admin);

}
