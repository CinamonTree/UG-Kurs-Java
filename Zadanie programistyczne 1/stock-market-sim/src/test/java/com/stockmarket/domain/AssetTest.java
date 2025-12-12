package com.stockmarket.domain;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssetTest {
    
    private Asset asset;
    private Price price;

    @BeforeEach
    void setup() {
        price = new Price(Currency.getInstance("USD"), 100.0);
        asset = new Asset("AAPL", price) {
            @Override
            public double getRealPrice() {
                return 100.0;
            }
        };
    }

    // Konstruktor

    @Test
    public void shouldCreateAsset() {
        Asset testAsset = new Asset("AAPL", price) {
            @Override
            public double getRealPrice() {
                return 100.0;
            }
        };
        assertEquals(price, testAsset.getPrice());
        assertEquals("AAPL", testAsset.getCode());
    }

    @Test
    public void shouldThrowExceptionWhenTryingToCreateAssetWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Asset wrongAsset = new Asset("AAPL", new Price(Currency.getInstance("USD"), -100.0)) {
                @Override
                public double getRealPrice() {
                    return 100.0;
                }
            };
        });
    }

    @Test
    public void shouldThrowExceptionWhenTryingToCreateAssetWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            Asset wrongAsset = new Asset("", price) {
                @Override
                public double getRealPrice() {
                    return 100.0;
                }
            };
        });
    }

    @Test
    public void shouldThrowExceptionWhenTryingToCreateAssetWithCodeAsNUllPointer() {
        assertThrows(IllegalArgumentException.class, () -> {
            Asset wrongAsset = new Asset(null, price) {
                @Override
                public double getRealPrice() {
                    return 100.0;
                }
            };
        });
    }

    // Metoda ustawiania ceny

    @Test
    void shouldSetPrice() {
        asset.setPrice(new Price(Currency.getInstance("USD"), 150.0));
        assertEquals(150.0, asset.getPrice().getAmount());
    }

    @Test
    public void shouldThrowExceptionWhenTryingToSetNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            asset.setPrice(new Price(Currency.getInstance("USD"), -100.0));
        });
    }

    // Metoda ustawiania kodu akcji

    @Test
    void shouldSetValidCode() {
        asset.setCode("ABC");
        assertEquals("ABC", asset.getCode());
    }

    @Test
    public void shouldThrowExceptionWhenTryingToSetEmptyStringCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            asset.setCode("");
        });
    }

    @Test
    public void shouldThrowExceptionWhenTryingToSetNullAsCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            asset.setCode(null);
        });
    }

    // Metoda toString

    @Test
    void shouldReturnAssetCodeAsString() {
        assertEquals("AAPL", asset.toString());
    }

    // Metoda equals

    @Test
    void shouldReturnTrueWhenComparingAssetsWithSameCode() {
        Asset anotherAsset = new Asset("AAPL", new Price(Currency.getInstance("USD"), 200.0)) {
            @Override
            public double getRealPrice() {
                return 200.0;
            }
        };
        assertEquals(asset, anotherAsset);
    }

    @Test
    void shouldReturnTrueWhenComparingWithSameInstance() {
        assertEquals(asset, asset);
    }  

    @Test
    void shouldReturnFalseWhenComparingWithOtherClass() {
        assertNotEquals(asset, new Object());
    }  

    // Metoda hashCode

    @Test
    void shouldHaveSameHashCodeForAssetsWithSameCode() {
        Asset anotherAsset = new Asset("AAPL", new Price(Currency.getInstance("USD"), 200.0)) {
            @Override
            public double getRealPrice() {
                return 200.0;
            }
        };
        assertEquals(asset.hashCode(), anotherAsset.hashCode());
    }

}