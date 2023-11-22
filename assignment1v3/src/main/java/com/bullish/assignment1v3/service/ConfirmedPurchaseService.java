package com.bullish.assignment1v3.service;

import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.AddableConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.ReadableConfirmedPurchaseService;

public class ConfirmedPurchaseService implements
AddableConfirmedPurchaseService, ReadableConfirmedPurchaseService{

    @Override
    public ConfirmedPurchaseRepository readConfirmedPurchase(ConfirmedPurchaseRepository confirmedPurchaseRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readConfirmedPurchase'");
    }

    @Override
    public void addConfirmedPurchase(ConfirmedPurchaseRepository confirmedPurchaseRepository) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addConfirmedPurchase'");
    }
    
}
