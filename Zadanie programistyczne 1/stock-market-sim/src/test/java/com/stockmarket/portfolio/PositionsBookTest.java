package com.stockmarket.portfolio;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stockmarket.domain.Asset;
import com.stockmarket.domain.Money;
import com.stockmarket.exceptions.AssetNotFoundInPositionsException;

public class PositionsBookTest {
    
    private PositionsBook positionsBook;
    private Asset assetInBook;
    private Asset asset;

    @BeforeEach
    public void setup() {
        positionsBook = new PositionsBook();
        Money price = new Money(Currency.getInstance("PLN"), 100.0);
        asset = new Asset("CDR", price){
            @Override
            public Money getRealPrice(int assetQuantity) {
                return price;
            }
        };
        Money price2 = new Money(Currency.getInstance("USD"), 200.0);
        assetInBook = new Asset("ORL", price2){
            @Override
            public Money getRealPrice(int assetQuantity) {
                return price;
            }
        };
        positionsBook.addPosition(assetInBook, 10);
    }

    //Konstruktor

    //Metoda addPosition
    @Test
    public void shouldAddAssetToPositionsBook() {
        positionsBook.addPosition(asset, 5);
        assertNotNull(positionsBook.getPosition(asset));
    }

    @Test
    public void shouldIncreaseQuantityWhenTryingToAddStockThatExistsInHoldings() {
        positionsBook.addPosition(assetInBook, 5);
        assertEquals(15, positionsBook.getPosition(assetInBook).getQuantity());
    }

    @Test
    public void shouldThrowExceptionWhenAddingStockWithNullPointer() {
        assertThrows(IllegalArgumentException.class, () -> {
            positionsBook.addPosition(null, 10);
        });
    }

    @Test
    public void shouldThrowExceptionWhenAddingStockWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            positionsBook.addPosition(asset, -5);
        });
    }

    //Metoda reducePosition
    @Test
    public void shouldReducePosition() {
        positionsBook.reducePosition(assetInBook, 5);
        assertEquals(5, positionsBook.getPosition(assetInBook).getQuantity());

    }

    @Test
    public void shouldThrowExceptionWhenReducingAssetWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            positionsBook.reducePosition(assetInBook, -5);
        });
    }

    @Test
    public void shouldRemovePositionWhenReducingPositionToZero() {
        positionsBook.reducePosition(assetInBook, 10);
        assertThrows(AssetNotFoundInPositionsException.class, () -> {
            positionsBook.getPosition(assetInBook);
        });
    }

    @Test
    public void shouldThrowExceptionWhenReducingPositionOfAssetNotInPositionsBook() {
        assertThrows(AssetNotFoundInPositionsException.class, () -> {
            positionsBook.reducePosition(asset, 5);
        });
    }

    @Test
    public void shouldThrowExceptionWhenReducingHigherQuantityThanExistsInPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            positionsBook.reducePosition(assetInBook, 15);
        });
    }

}
