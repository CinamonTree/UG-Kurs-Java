package com.stockmarket.domain;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceTest {
    
    private Price USD10;
    private Price USD20;
    private Price EUR10;
    private Currency USD;
    private Currency EUR;

    @BeforeEach
    public void setUp() {
        USD = Currency.getInstance("USD");
        EUR = Currency.getInstance("EUR");
        USD10 = new Price(USD, 10.0);
        USD20 = new Price(USD, 20.0);
        EUR10 = new Price(EUR, 10.0);
    }
        
    // Konstruktor

    @Test
    public void shouldCreatePrice() {
        Price price = new Price(USD, 10.0);

        assertEquals(USD, price.getCurrency());
        assertEquals(10.0, price.getAmount());
    }

    @Test
    public void shouldThrowExceptionWhenCreatingPriceWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,() -> {
            new Price(USD, -5.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenCreatingPriceWithNullPointerCurrency() {
        assertThrows(IllegalArgumentException.class,() -> {
            new Price(null, 10.0);
        });
    }

    // Metoda setCurrency

    @Test
    public void shouldSetCurrency() {
        USD10.setCurrency(EUR);
        assertEquals(EUR, USD10.getCurrency());
    }

    @Test
    public void shouldThrowExceptionWhenSettingCurrencyAsNullPointer() {
        assertThrows(IllegalArgumentException.class,() -> {
            USD10.setCurrency(null);
        });
    }

    // metoda setAmount

    @Test
    public void shouldSetAmount() {
        USD10.setAmount(15.0);
        assertEquals(15.0, USD10.getAmount());
    }

    @Test
    public void shouldThrowExceptionWhenSettingNegativeAmount() {
        assertThrows(IllegalArgumentException.class,() -> {
            USD10.setAmount(-10.0);
        });
    }

    // Metoda equals

    @Test
    public void shouldComparePricesByCurrencyAndAmount() {
        Price anotherUSD10 = new Price(USD, 10.0);
        assertEquals(USD10, anotherUSD10);
    }

    @Test
    public void shouldNotConsiderPricesWithDifferentCurrenciesAsEqual() {
        Price anotherEUR10 = new Price(EUR, 10.0);
        assertEquals(false, USD10.equals(anotherEUR10));
    }

    @Test
    public void shouldNotConsiderPricesWithDifferentAmountsAsEqual() {
        Price anotherUSD15 = new Price(USD, 15.0);
        assertEquals(false, USD10.equals(anotherUSD15));
    }

    @Test
    public void shouldReturnFalseWhenComparingWithOtherClass() {
        assertFalse(USD10.equals(new Object()));
    }

    @Test
    public void shouldReturnFalseWhenComparingWithNull() {
        assertFalse(USD10.equals(null));
    }

    // Metoda hashCode

    @Test
    public void shouldGenerateHashCodeBasedOnCurrencyAndAmount() {
        Price anotherUSD10 = new Price(USD, 10.0);
        assertEquals(USD10.hashCode(), anotherUSD10.hashCode());
    }

    // Metoda compareTo

    @Test
    public void shouldComparePricesCorrectly() {
        assertEquals(-1, USD10.compareTo(USD20));
        assertEquals(1, USD20.compareTo(USD10));
        assertEquals(0, USD10.compareTo(new Price(USD, 10.0)));
    }

    @Test
    public void shouldThrowExceptionWhenComparingPricesWithDiffrentCurrencies() {
        assertThrows(IllegalArgumentException.class, () -> {
            USD10.compareTo(EUR10);
        });
    }

    // Metoda toString

    @Test
    public void shouldReturnStringRepresentationOfPrice() {
        assertEquals("10.0 USD", USD10.toString());
    }

}
