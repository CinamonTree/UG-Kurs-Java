package com.stockmarket.domain;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class ComodityTest {

    private Comodity gold;
    private Price price;
    private Currency currency;

    @BeforeEach
    public void setUp() {
        currency = Currency.getInstance("PLN");
        price = new Price(currency, 100.0);
        gold = new Comodity("GLD", "Gold", price, 5.0);
        
    }

    // Konstruktor

    @Test
    public void shouldCreateComodity() {
        Comodity newGold = new Comodity("GLD", "Gold", price, 5.0);

        assertEquals("Gold", gold.getName());
        assertEquals(5.0, gold.getHandlingFee(), 0.0001);
        assertEquals(price, gold.getPrice());
    }

    @Test
    public void shouldThrowExceptionWhenCreatingComodityWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Comodity("OIL", "", price, 1.0));
    }

    @Test
    public void shouldThrowExceptionWhenCreatingComodityWithNullPointerName() {
        assertThrows(IllegalArgumentException.class, () -> new Comodity("OIL", null, price, 1.0));
    }

    @Test
    public void shouldThrowExceptionWhenCreatingComodityWithNegativeHandlingFee() {
        assertThrows(IllegalArgumentException.class, () -> new Comodity("OIL", "Oil", price, -0.01));
    }

    // Metoda setHandlingFee

    @Test
    public void shouldSetHandlingFee() {
        gold.setHandlingFee(10.0);
        assertEquals(10.0, gold.getHandlingFee(), 0.0001);
    }

    @Test
    public void shouldThrowExceptionWhenSettingNegativeHandlingFee() {
        assertThrows(IllegalArgumentException.class, () -> {
            gold.setHandlingFee(-2.0);
        });
    }

    // Metoda setName

    @Test
    public void shouldSetName() {
        gold.setName("Silver");
        assertEquals("Silver", gold.getName());
    }

    @Test
    public void shouldThrowExceptionWhenSettingEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            gold.setName("  ");
        });
    }

    // Metoda getRealPrice

    @ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource({
        "1, 105.0",
        "5, 525.0",
        "10, 1050.0",
    })
    public void shouldCalculateRealPrice(int quantity, double expected) {
        Price real = gold.getRealPrice(quantity);
        assertEquals(expected, real.getAmount(), 0.0001);
    }

    @Test
    public void shouldThrowExceptionWhenCalculatingRealPriceWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            gold.getRealPrice(-3);
        });
    }

}
