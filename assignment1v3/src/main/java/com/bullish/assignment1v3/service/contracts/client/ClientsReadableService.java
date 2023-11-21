package com.bullish.assignment1v3.service.contracts.client;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.users.Client;

public interface ClientsReadableService {
    public ResponseEntity<List<Client>> readAllClients();
}
