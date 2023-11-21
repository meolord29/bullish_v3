package com.bullish.assignment1v3.service.contracts.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminsReadableService {
    public ResponseEntity<List<Admin>> readAllAdmins();
}
