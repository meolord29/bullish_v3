package com.bullish.assignment1v3.service.contracts.client;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Client;

public interface ClientAddableService {
    public ResponseEntity<Client> addClient(Client client);
}
