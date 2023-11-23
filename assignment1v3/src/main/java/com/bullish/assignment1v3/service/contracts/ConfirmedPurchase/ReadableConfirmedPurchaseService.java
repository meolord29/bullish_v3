package com.bullish.assignment1v3.service.contracts.ConfirmedPurchase;

import java.util.Optional;

import com.bullish.assignment1v3.model.store.ConfirmedPurchase;

public interface ReadableConfirmedPurchaseService {
    public Optional<ConfirmedPurchase> readConfirmedPurchase(ConfirmedPurchase confirmedPurchaseUpdated);
}
