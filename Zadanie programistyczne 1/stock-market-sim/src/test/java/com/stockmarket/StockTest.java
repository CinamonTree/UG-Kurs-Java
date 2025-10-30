package com.stockmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StockTest {

    @Test
    public void shouldCreateStock() {
        Stock exampleStock = new Stock("CDR", "CD Project RED", 256.80);

        assertEquals("CDR", exampleStock.getCode());
        assertEquals("CD Project RED", exampleStock.getCompanyName());
        assertEquals(256.80, exampleStock.getValue(), 0.001);
    }

    @Test
    public void shouldCompareObjectsBySymbol() {
        Stock exampleStock1 = new Stock("CDR", "CD Project RED", 256.80);
        Stock exampleStock2 = new Stock("CDR", "Cdep", 125.67);

        assertTrue(exampleStock1.equals(exampleStock2));
        assertEquals(exampleStock1.hashCode(), exampleStock2.hashCode());
    }

    @Test
    public void shouldReturnFalseWhenTwoStocksWithDiffrentCodesAreCompared() {
        Stock exampleStock1 = new Stock("CDR", "CD Projekt RED", 256.80);
        Stock exampleStock2 = new Stock("ORL", "Orlen", 256.80);

        assertFalse(exampleStock1.equals(exampleStock2));
    }

    @Test
    public void shouldReturnFalseWhenComparedWithNull() {
        Stock stock = new Stock("CDR", "CD Projekt", 256.80);
        assertFalse(stock.equals(null));
    }

    @Test
    public void shouldReturnFalseWhenComparedWithDifferentType() {
        Stock stock = new Stock("CDR", "CD Projekt", 256.80);
        String notAStock = "CDR";
        assertFalse(stock.equals(notAStock));
    }

    @Test
    public void shouldBeImmutableAfterCreation() {
        Stock stock = new Stock("CDR", "CD Projekt", 256.80);
        assertEquals("CDR", stock.getCode());
        assertEquals("CD Projekt", stock.getCompanyName());
        assertEquals(256.80, stock.getValue(), 0.001);
    }

    @Test
    public void shouldBeEqualToItself() {
        Stock stock = new Stock("CDR", "CD Projekt", 256.80);
        assertTrue(stock.equals(stock));
    }
    
}
