package com.bullish.assignment1v3.service.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminsReadable {
    public ResponseEntity<List<Admin>> readAllAdmins();
}
