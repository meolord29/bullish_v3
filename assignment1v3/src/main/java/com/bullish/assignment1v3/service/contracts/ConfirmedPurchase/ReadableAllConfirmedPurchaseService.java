package com.bullish.assignment1v3.service.contracts.ConfirmedPurchase;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.ConfirmedPurchase;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;

public interface ReadableConfirmedPurchaseService {

    public ResponseEntity<List<ConfirmedPurchase>> readConfirmedPurchase(String clientUsername);

}
