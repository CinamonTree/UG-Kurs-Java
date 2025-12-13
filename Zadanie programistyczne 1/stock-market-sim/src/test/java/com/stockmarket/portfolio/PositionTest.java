package com.stockmarket.portfolio;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stockmarket.domain.Price;
import com.stockmarket.domain.Asset;

public class PositionTest {

    private Asset asset;
    private Position position;
    
    @BeforeEach
    void setup() {
        Price price = new Price(Currency.getInstance("USD"), 100.0);
        asset = new Asset("CDR", price) {
            @Override
            public Price getRealPrice(int assetQuantity) {
                return price;
            }
        };
        position = new Position(asset, 10);
    }

    // Konstruktor
    @Test
    public void shouldCreatePosition() {
        Position newPosition = new Position(asset, 15);

        assertEquals(asset, newPosition.getAsset());
        assertEquals(15, newPosition.getQuantity());
    }

    @Test
    public void shouldNotAllowCreatingStockHoldingWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new Position(asset, -5));
    }

    @Test
    public void shouldNotAllowCreatingStockHoldingWithNullStock() {
        assertThrows(IllegalArgumentException.class, () -> new Position(null, 10));
    }

    // Metoda dodawania ilości akcji
    @Test
    public void shouldAddQuantityCorrectly() {
        position.addQuantity(5);
        assertEquals(15, position.getQuantity());
    }

    @Test
    public void shouldNotAllowAddingNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> position.addQuantity(-3));
    }

    // Metoda zmniejszania ilości akcji
    @Test
    public void shouldReduceQuantityCorrectly() {
        position.reduceQuantity(4);
        assertEquals(6, position.getQuantity());
    }

    @Test
    public void shouldNotAllowReducingQuantityBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> position.reduceQuantity(15));
    }

}
