package com.stockmarket.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockHoldingTest {

    private Stock stock;
    private StockHolding stockHolding;
    
    @BeforeEach
    void setup() {
        stock = new Stock("CDR", "CD Project", 150.0);
        stockHolding = new StockHolding(stock, 10);
    }

    // Konstruktor
    @Test
    public void shouldCreateStockHolding() {
        StockHolding holding = new StockHolding(stock, 15);

        assertEquals(stock, holding.getStock());
        assertEquals(15, holding.getQuantity());
    }

    @Test
    public void shouldNotAllowCreatingStockHoldingWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new StockHolding(stock, -5));
    }

    @Test
    public void shouldNotAllowCreatingStockHoldingWithNullStock() {
        assertThrows(IllegalArgumentException.class, () -> new StockHolding(null, 10));
    }

    // Metoda dodawania ilości akcji
    @Test
    public void shouldAddQuantityCorrectly() {
        stockHolding.addQuantity(5);
        assertEquals(15, stockHolding.getQuantity());
    }

    @Test
    public void shouldNotAllowAddingNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> stockHolding.addQuantity(-3));
    }

    // Metoda zmniejszania ilości akcji
    @Test
    public void shouldReduceQuantityCorrectly() {
        stockHolding.reduceQuantity(4);
        assertEquals(6, stockHolding.getQuantity());
    }

    @Test
    public void shouldNotAllowReducingQuantityBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> stockHolding.reduceQuantity(15));
    }

}
