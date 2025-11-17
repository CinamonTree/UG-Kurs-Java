package com.stockmarket.exceptions;

public class PortfolioWalletISFullException extends RuntimeException {
    public PortfolioWalletISFullException(String message) {
        super(message);
    }
}
