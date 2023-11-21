package com.bullish.assignment1v3.service.contracts.client;

import java.util.Optional;

import com.bullish.assignment1v3.model.users.Client;

public interface ClientReadableService {
    public Optional<Client> readClient(String username);
}
