package com.stockmarket.domain;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ShareTest {

    private Share share;
    private Currency USD;
    private Money price;

    @BeforeEach
    public void setup() {
        USD = Currency.getInstance("USD");
        price = new Money(USD, 100.0);
        share = new Share("AAPL", "Apple Inc.", price, 5.0);
    }

    // Konstruktor

    @Test
    public void shouldCreateShare() {
        Currency USD = Currency.getInstance("USD");
        Money price = new Money(USD, 100.0);
        Share share = new Share("AAPL", "Apple Inc.", price, 5.0);

        assertEquals("AAPL", share.getCode());
        assertEquals("Apple Inc.", share.getCompanyName());
        assertEquals(price, share.getPrice());
        assertEquals(5.0, share.getManipulationFee());
    }

    @Test
    public void shouldThrowExceptionWhenCreatingShareWithEmptyCompanyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Share("AAPL", "", price, 5.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenCreatingShareWithNullPointerCompanyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Share("AAPL", null, price, 5.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenCreatingShareWithNegativeManipulationFee() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Share("AAPL", "Apple Inc.", price, -5.0);
        });
    }

    // Metoda setManipulationFee

    @Test
    public void shouldSetManipulationFee() {
        share.setManipulationFee(10.0);
        assertEquals(10.0, share.getManipulationFee());
    }

    @Test
    public void shouldThrowExceptionWhenSettingNegativeManipulationFee() {
        assertThrows(IllegalArgumentException.class, () -> {
            share.setManipulationFee(-10.0);
        });
    }

    // Metoda setCompanyName

    @Test
    public void shouldSetCompanyName() {
        share.setCompanyName("New Company Name");
        assertEquals("New Company Name", share.getCompanyName());
    }

    @Test
    public void shouldThrowExceptionWhenSettingEmptyCompanyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            share.setCompanyName("");
        });
    }

    @Test
    public void shouldThrowExceptionWhenSettingNullPointerCompanyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            share.setCompanyName(null);
        });
    }

    // Nadpisanie metody getRealPrice

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5})
    public void shouldCalculateRealPrice(int quantity) {
        Money realPrice = share.getRealPrice(quantity);
        assert(realPrice.getAmount() == 100.0 * quantity + 5.0);
    }

    @Test
    public void shouldThrowExceptionWhenCalculaitingRealPriceWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            share.getRealPrice(-1);
        });
    }

}
