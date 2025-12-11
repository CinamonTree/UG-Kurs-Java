package com.stockmarket.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CurrencyTest {

    private Currency USD;
    private Currency EUR;
    private Currency USDDuplicate;

    @BeforeEach
    void setup() {
        USD = new Currency("USD", "United States Dollar");
        EUR = new Currency("EUR", "Euro");
        USDDuplicate = new Currency("USD", "Dolars");
    }

    // Konstruktor

    @Test
    public void shouldCreateCurrency() {
        Currency currency = new Currency("USD", "United States Dollar");
        assertEquals("USD", currency.getCode());
        assertEquals("United States Dollar", currency.getName());
    }

    @Test
    public void shouldThrowExceptionWhenCreatingCurrencyWithEmptyCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Currency("", "United States Dollar");
        });
    }

    @Test
    public void shouldThrowExceptionWhenCreatingCurrencyWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Currency("USD", "");
        });
    }

    // Metoda setCode

    @Test
    public void shouldSetCode() {
        USD.setCode("EUR");
        assertEquals("EUR", USD.getCode());
    }

    @Test
    public void shouldThrowExceptionWhenSettingEmptyCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            USD.setCode("");
        });
    }

    // Metoda setName

    @Test
    public void shouldSetName() {
        USD.setName("US Dollar");
        assertEquals("US Dollar", USD.getName());
    }

    @Test
    public void shouldThrowExceptionWhenSettingEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            USD.setName("");
        });
    }

    // ToString

    @Test
    public void shouldReturnCurrencyCodeAsString() {
        assertEquals("USD", USD.toString());
    }

    // Metoda equals i hashCode

    @Test
    public void shouldCompareCurrenciesByCode() {
        assertEquals(USD, USDDuplicate);
        assertEquals(USD.hashCode(), USDDuplicate.hashCode());
    }

}
