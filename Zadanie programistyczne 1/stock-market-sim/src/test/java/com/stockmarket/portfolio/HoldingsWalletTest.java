package com.stockmarket.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stockmarket.exceptions.StockNotFoundInHoldingsException;

public class HoldingsWalletTest {
    
    private HoldingsWallet holdingsWallet;
    private Stock stockInHoldings;
    private Stock stock;

    @BeforeEach
    public void setUp() {
        holdingsWallet = new HoldingsWallet();
        stock = new Stock("ORN", "Orlen", 250.0);
        stockInHoldings = new Stock("CDR", "CD Project Red", 150.0);
        holdingsWallet.addHolding(stockInHoldings, 10);
    }

    // Metoda dodawania akcji do portfela
    @Test
    public void shouldAddStockToHodlings() {
        holdingsWallet.addHolding(stock, 5);
        assertEquals(stock, holdingsWallet.getHolding(stock).getStock());
        assertEquals(5, holdingsWallet.getHolding(stock).getQuantity());
    }

    @Test
    public void shouldIncreaseQuantityWhenTryingToAddStockThatExistsInHoldings() {
        holdingsWallet.addHolding(stockInHoldings, 5);
        assertEquals(15, holdingsWallet.getHolding(stockInHoldings).getQuantity());
    }

    @Test
    public void shouldThrowExceptionWhenAddingStockWithNullPointer() {
        assertThrows(IllegalArgumentException.class, 
        () -> holdingsWallet.addHolding(null, 10));
    }

    @Test
    public void shouldThrowExceptionWhenAddingStockWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, 
        () -> holdingsWallet.addHolding(stock, -10));
    }

    // Metoda usuwania akcji z portfela
    @Test
    public void shouldThrowExceptionWhenRemovingStockWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, 
        () -> holdingsWallet.reduceHolding(stockInHoldings, -10));
    }

    @Test
    public void shouldThrowExceptionWhenRemovingStockWithNullPointer() {
        assertThrows(IllegalArgumentException.class, 
        () -> holdingsWallet.reduceHolding(null, 10));
    }

    @Test
    public void shouldReduceQuantityOfHolding() {
        holdingsWallet.reduceHolding(stockInHoldings, 3);
        assertEquals(7, holdingsWallet.getHolding(stockInHoldings).getQuantity());
    }

    @Test
    public void shouldRemoveHoldingWhenReducingItsQuantityToZero() {
        holdingsWallet.reduceHolding(stockInHoldings, 10);
        assertThrows(StockNotFoundInHoldingsException.class, 
        () -> holdingsWallet.getHolding(stockInHoldings));
    }

    @Test
    public void shouldThrowExceptionWhenTryingToReduceHoldingBelowItsQuantity() {
        assertThrows(IllegalArgumentException.class, 
        () -> holdingsWallet.reduceHolding(null, 15));
    }

}
