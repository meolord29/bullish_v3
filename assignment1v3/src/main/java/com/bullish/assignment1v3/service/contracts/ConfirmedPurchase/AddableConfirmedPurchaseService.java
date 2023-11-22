package com.bullish.assignment1v3.service.contracts.ConfirmedPurchase;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.ConfirmedPurchase;

public interface AddableConfirmedPurchaseService {

    public ResponseEntity<ConfirmedPurchase> addConfirmedPurchase(ConfirmedPurchase ConfirmedPurchase);

}
