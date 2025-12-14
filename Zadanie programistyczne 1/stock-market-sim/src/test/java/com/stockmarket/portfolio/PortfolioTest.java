package com.stockmarket.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.stockmarket.exceptions.NotEnoughFundsException;

public class PortfolioTest {

    // TODO: wyciągnąć powtarzający się kod do metod pomocniczych

    // Konstruktor
    @Test
    public void shouldCreateEmptyPortfolioWithInitialCash() {
        Portfolio portfolio = new Portfolio(1000.0);

        assertEquals(1000.0, portfolio.getCash(), 0.001);
        assertEquals(0, portfolio.getTotalHoldingsCount());
        assertEquals(0.0, portfolio.calculateStockValue(), 0.001);
        assertEquals(1000.0, portfolio.calculatePortfolioValue(), 0.001);
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
        assertThrows(NotEnoughFundsException.class, () -> portfolio.withdrawCash(1500.0));
    }

    @Test
    public void shouldNotAllowToWithdrawNegativeAmountOfCash() {
        Portfolio portfolio = new Portfolio(1000.0);
        assertThrows(NotEnoughFundsException.class, () -> portfolio.withdrawCash(-200.0));
    }

    // Metoda dodawania akcji
    @Test
    public void shouldAddStockCorrectly(){
        Portfolio portfolio = new Portfolio(1000.0);
        Stock stock = new Stock("CDR", "CD Projekt", 100.0);
        portfolio.addStock(stock, 5);
        assertEquals(5, portfolio.getTotalHoldingsCount());
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

    //Metoda zliczająca całkowitą ilość akcji w portfelu
    @Test
    public void shouldGetTotalHoldingsCountCorrectly() {
        Portfolio portfolio = new Portfolio(1000.0);
        Stock stockA = new Stock("CDR", "CD Projekt", 100.0);
        Stock stockB = new Stock("ORLN", "Orlen", 200.0);
        portfolio.addStock(stockA, 2);
        portfolio.addStock(stockB, 3);
        assertEquals(5, portfolio.getTotalHoldingsCount());
    }

    // Metoda obliczająca wartość akcji w portfelu
    @Test
    public void shouldCalculateStockValueCorrectly() {
        Portfolio portfolio = new Portfolio(1000.0);
        Stock stockA = new Stock("CDR", "CD Projekt", 100.0);
        Stock stockB = new Stock("ORLN", "Orlen", 200.0);

        portfolio.addStock(stockA, 2);
        portfolio.addStock(stockB, 3);

        assertEquals(800.0, portfolio.calculateStockValue(), 0.001);
    }

    // Metoda obliczająca wartość całego portfolio
    @Test
    public void shouldCalculateTotalValueCorrectly() {
        Portfolio portfolio = new Portfolio(500.0);
        Stock stock = new Stock("CDR", "CD Projekt", 100.0);

        portfolio.addStock(stock, 2);
        assertEquals(700.0, portfolio.calculatePortfolioValue(), 0.001);
    }

}