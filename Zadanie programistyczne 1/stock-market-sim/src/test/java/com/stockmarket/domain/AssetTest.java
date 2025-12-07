package com.stockmarket.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssetTest {
    
    private Asset asset;

    @BeforeEach
    void setup() {
        asset = new Asset("AAPL", 100.0) {
            @Override
            public double getRealPrice() {
                return 100.0;
            }
        };
    }

    // Konstruktor

    @Test
    public void shouldCreateAsset() {
        Asset testAsset = new Asset("AAPL", 100.0) {
            @Override
            public double getRealPrice() {
                return 100.0;
            }
        };
        assertEquals(100.0, testAsset.getPrice());
        assertEquals("AAPL", testAsset.getCode());
    }

    @Test
    public void shouldThrowExceptionWhenTryingToCreateAssetWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            Asset wrongAsset = new Asset("AAPL", -0.50) {
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
            Asset wrongAsset = new Asset("", 100.0) {
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
            Asset wrongAsset = new Asset(null, 100.0) {
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
        asset.setPrice(150.0);
        assertEquals(150.0, asset.getPrice());
    }

    @Test
    public void shouldThrowExceptionWhenTryingToSetNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            asset.setPrice(-50.0);
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

}