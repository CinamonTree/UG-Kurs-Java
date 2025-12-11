package com.stockmarket.portfolio;

import java.util.ArrayList;

import com.stockmarket.exceptions.StockNotFoundInHoldingsException;

/**
* Klasa Holdings reprezentuję cały zestaw akcji posiadanych w portfolio
*/

public class HoldingsWallet {

    private final ArrayList<StockHolding> holdingsList;

    public HoldingsWallet() {
        this.holdingsList = new ArrayList<>();
    }

    public void addHolding(Stock stock, int quantity) {
        validateStockNotNull(stock);
        StockHolding stockHolding = findHolding(stock);
        if (stockHolding != null) {
            stockHolding.addQuantity(quantity);
        } else {
            holdingsList.add(new StockHolding(stock, quantity));
        }
    }

    public void reduceHolding(Stock stock, int quantity) throws StockNotFoundInHoldingsException {
        validateStockNotNull(stock);
        StockHolding stockHolding = findHolding(stock);

        if (stockHolding == null) {
            throw new StockNotFoundInHoldingsException("Nie można zmniejszyć ilości akcji którch nie ma w portlefu.");
        }
        
        if (stockHolding.getQuantity() == quantity){
            holdingsList.remove(stockHolding);
        } else {
            stockHolding.reduceQuantity(quantity);
        }
    }

    public StockHolding getHolding(Stock stock) throws StockNotFoundInHoldingsException {
        validateStockNotNull(stock);
        StockHolding stockHolding = findHolding(stock);
        if (stockHolding != null) {
            return stockHolding;
        }
        throw new StockNotFoundInHoldingsException("Nie znaleziono podanych akcji w portfelu.");
    }

    public Iterable<StockHolding> getAllHoldings() {
        return holdingsList;
    }

    private StockHolding findHolding(Stock stockToFind) {
        for (StockHolding holding : holdingsList) {
            if (holding.getStock().equals(stockToFind)) {
                return holding;
            }
        }
        return null;
    }

    private Stock validateStockNotNull(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Oczekiwano instancji Stock, przekazano null.");
        }
        return stock;
    }

}
