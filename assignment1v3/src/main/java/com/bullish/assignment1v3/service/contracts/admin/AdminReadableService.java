package com.bullish.assignment1v3.service.contracts.admin;

import java.util.Optional;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminReadableService {

    public Optional<Admin> readAdmin(String username);

}
