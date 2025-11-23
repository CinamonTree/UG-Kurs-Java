package com.stockmarket.exceptions;

public class StockNotFoundInHoldingsException extends RuntimeException {
    public StockNotFoundInHoldingsException(String message) {
        super(message);
    }
}
