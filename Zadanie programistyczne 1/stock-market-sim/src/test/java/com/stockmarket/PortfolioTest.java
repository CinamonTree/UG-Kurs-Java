package com.stockmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.stockmarket.exceptions.NotEnoughCashException;

public class PortfolioTest {

    // TODO: wyciągnąć powtarzający się kod do metod pomocniczych

    // Konstruktor
    @Test
    public void shouldCreateEmptyPortfolioWithInitialCash() {
        Portfolio portfolio = new Portfolio(1000.0);

        assertEquals(1000.0, portfolio.getCash(), 0.001);
        assertEquals(0, portfolio.getHoldingsCount());
        assertEquals(0.0, portfolio.calculateTotalStockValue(), 0.001);
        assertEquals(1000.0, portfolio.calculateTotalValue(), 0.001);
    }

    // Metoda dodawania gotówki
    @Test
    public void shouldNotAllowToAddNegativeAmountOfCash() {
        Portfolio portfolio = new Portfolio(1000.0);
        assertThrows(IllegalArgumentException.class, () -> portfolio.addCash(-500.0));
    }

    @Test
    public void shouldAddCashCorrectly() {
        Portfolio portfolio = new Portfolio(1000.0);
        portfolio.addCash(500.0);
        assertEquals(1500.0, portfolio.getCash(), 0.001);
    }

    // Metoda wypłacania gotówki
    @Test
    public void shouldWithdrawCashCorrectly() {
        Portfolio portfolio = new Portfolio(1000.0);
        portfolio.withdrawCash(400.0);
        assertEquals(600.0, portfolio.getCash(), 0.001);
    }

    @Test
    public void shouldNotAllowToWithdrawMoreCashThanAvailable() {
        Portfolio portfolio = new Portfolio(1000.0);
        assertThrows(NotEnoughCashException.class, () -> portfolio.withdrawCash(1500.0));
    }

    @Test
    public void shouldNotAllowToWithdrawNegativeAmountOfCash() {
        Portfolio portfolio = new Portfolio(1000.0);
        assertThrows(IllegalArgumentException.class, () -> portfolio.withdrawCash(-200.0));
    }

    // Metoda dodawania akcji
    @Test
    public void shouldAddNewStockToPortfolio() {
        Portfolio portfolio = new Portfolio(1000.0);
        Stock stock = new Stock("CDR", "CD Projekt", 250.0);

        portfolio.addStock(stock, 5);

        assertEquals(1, portfolio.getHoldingsCount());
        assertEquals(5, portfolio.getStockQuantity(stock));
    }

    @Test
    public void shouldIncreaseQuantityWhenSameStockAddedAgain() {
        Portfolio portfolio = new Portfolio(1000.0);
        Stock stock = new Stock("CDR", "CD Projekt", 250.0);

        portfolio.addStock(stock, 3);
        portfolio.addStock(stock, 2);

        assertEquals(1, portfolio.getHoldingsCount());
        assertEquals(5, portfolio.getStockQuantity(stock));
    }

    @Test
    public void shouldStoreMultipleDifferentStocksWhenAdded() {
        Portfolio portfolio = new Portfolio(1000.0);
        Stock stockA = new Stock("CDR", "CD Projekt", 250.0);
        Stock stockB = new Stock("ORLN", "Orlen", 400.0);

        portfolio.addStock(stockA, 3);
        portfolio.addStock(stockB, 2);

        assertEquals(2, portfolio.getHoldingsCount());
        assertEquals(3, portfolio.getStockQuantity(stockA));
        assertEquals(2, portfolio.getStockQuantity(stockB));
    }

    @Test
    public void shouldThrowExceptionWhenAddingStockWithNullPointer() {
        Portfolio portfolio = new Portfolio(1000.0);
        assertThrows(IllegalArgumentException.class, () -> portfolio.addStock(null, 10));
    }

    @Test
    public void shouldThrowExceptionWhenAddingStockWithNegativeQuantity() {
        Portfolio portfolio = new Portfolio(1000.0);
        Stock stock = new Stock("CDR", "CD Projekt", 100.0);
        assertThrows(IllegalArgumentException.class, () -> portfolio.addStock(stock, -10));
    }

    // Metoda obliczająca wartość akcji w portfelu
    @Test
    public void shouldCalculateStockValueCorrectly() {
        Portfolio portfolio = new Portfolio(1000.0);
        Stock stockA = new Stock("CDR", "CD Projekt", 100.0);
        Stock stockB = new Stock("ORLN", "Orlen", 200.0);

        portfolio.addStock(stockA, 2);
        portfolio.addStock(stockB, 3);

        assertEquals(800.0, portfolio.calculateTotalStockValue(), 0.001);
    }

    // Metoda obliczająca wartość całego portfolio
    @Test
    public void shouldCalculateTotalValueCorrectly() {
        Portfolio portfolio = new Portfolio(500.0);
        Stock stock = new Stock("CDR", "CD Projekt", 100.0);

        portfolio.addStock(stock, 2);
        assertEquals(700.0, portfolio.calculateTotalValue(), 0.001);
    }

    // Metoda zwracająca ilość posiadanych akcji danego typu
    @Test
    public void shouldReturnZeroQuantityForMissingStock() {
        Portfolio portfolio = new Portfolio(1000.0);
        Stock missing = new Stock("XYZ", "Nieistniejąca spółka", 10.0);

        assertEquals(0, portfolio.getStockQuantity(missing));
    }

    @Test
    public void shouldReturnZeroStockValueWhenNoHoldings() {
        Portfolio portfolio = new Portfolio(1000.0);
        assertEquals(0.0, portfolio.calculateTotalStockValue(), 0.001);
    }

}