package com.bullish.assignment1v3.service.contracts.ConfirmedPurchase;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.ConfirmedPurchase;

public interface ReadableAllConfirmedPurchaseService {

    public ResponseEntity<List<ConfirmedPurchase>> readAllConfirmedPurchase(String clientUsername);

}
