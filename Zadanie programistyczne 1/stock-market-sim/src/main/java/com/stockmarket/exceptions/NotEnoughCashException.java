package com.stockmarket.exceptions;

public class NotEnoughCashException extends RuntimeException {
    public NotEnoughCashException(String message) {
        super(message);
    }
}
