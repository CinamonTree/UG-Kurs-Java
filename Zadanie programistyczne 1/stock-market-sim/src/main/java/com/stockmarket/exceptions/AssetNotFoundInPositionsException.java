package com.stockmarket.exceptions;

public class AssetNotFoundInPositionsException extends RuntimeException {
    public AssetNotFoundInPositionsException(String message) {
        super(message);
    }
}
