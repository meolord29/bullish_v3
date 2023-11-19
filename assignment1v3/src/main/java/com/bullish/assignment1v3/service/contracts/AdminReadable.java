package com.bullish.assignment1v3.service.contracts;

import java.util.Optional;

import com.bullish.assignment1v3.model.users.Admin;

public interface AdminReadable {

    public Optional<Admin> readAdmin(String username);

}
