package com.bullish.assignment1v3.controller.adminExceptions;

public class AdminNotFoundException extends RuntimeException {

    public AdminNotFoundException(Long id) {
        super("Could not find admin " + id);
    }
}
