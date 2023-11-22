package com.bullish.assignment1v3.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.AddableConfirmedPurchaseService;
import com.bullish.assignment1v3.service.contracts.ConfirmedPurchase.ReadableConfirmedPurchaseService;

public class ConfirmedPurchaseService implements
AddableConfirmedPurchaseService, ReadableConfirmedPurchaseService{

    private ConfirmedPurchaseRepository confirmedPurchaseRepository;

    @Autowired
    private BasketCalculateService basketCalculateService;

    public ConfirmedPurchaseService(ConfirmedPurchaseRepository confirmedPurchaseRepository) {
        this.confirmedPurchaseRepository = confirmedPurchaseRepository;
    }


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
