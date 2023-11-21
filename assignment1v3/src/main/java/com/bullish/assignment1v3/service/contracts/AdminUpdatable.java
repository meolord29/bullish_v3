package com.bullish.assignment1v3.service.contracts;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminUpdatable {

    public ResponseEntity<Admin> updateAdmin(Admin admin);

}
