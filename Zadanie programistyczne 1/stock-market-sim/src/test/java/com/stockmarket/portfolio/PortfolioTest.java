package com.stockmarket.portfolio;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stockmarket.domain.Comodity;
import com.stockmarket.domain.Money;
import com.stockmarket.domain.Share;

class PortfolioTest {

    private Portfolio portfolio;
    private Currency usd;
    private Share apple;
    private Comodity oil;

    @BeforeEach
    void setUp() {
        usd = Currency.getInstance("USD");
        portfolio = new Portfolio(10000.0, usd);
        apple = new Share("AAPL", "Apple Inc.", new Money(usd, 100.0), 5.0);
        oil = new Comodity("OIL", "Oil", new Money(usd, 20.0), 2.0);
    }

    // Konstruktor

    @Test
    void shouldCreatePortfolioWithInitialCash() {
        Money money = portfolio.getCashWallet().getMoney();

        assertEquals(10000.0, money.getAmount());
        assertEquals(usd, money.getCurrency());
    }

    // Metoda BuyAsset

    @Test
    void shouldBuyAsset() {
        portfolio.buyAsset(apple, 10);

        Money cashAfterBuy = portfolio.getCashWallet().getMoney();
        assertEquals(8995.0, cashAfterBuy.getAmount());

        assertNotNull(
            portfolio.getPositionsBook().getPosition(apple)
        );
    }

    @Test
    void shouldThrowExceptionWhenNotEnoughCashToBuyAsset() {
        assertThrows(
        RuntimeException.class,() -> {
            portfolio.buyAsset(apple, 200);
        });
    }

    // Metoda sellAsset

    @Test
    void shouldSellAsset() {
        portfolio.buyAsset(apple, 10);
        portfolio.sellAsset(apple, 5);

        Money cashAfterSell = portfolio.getCashWallet().getMoney();
        assertEquals(9495.0, cashAfterSell.getAmount());
    }

    // Polimorfizm Asset

    @Test
    void shouldCalculateDifferentPricesForDifferentAssetTypes() {
        apple.setPrice(new Money(usd, 100.0));
        oil.setPrice(new Money(usd, 100.0));
        
        portfolio.buyAsset(apple, 10);

        assertEquals(portfolio.getCashWallet().getMoney().getAmount(), 8995.0);

        portfolio.buyAsset(oil, 10);

        assertEquals(portfolio.getCashWallet().getMoney().getAmount(), 7975.0);
    }

    @Test
    void shouldHandlePolymorphicAssetPricingOnSell() {
        portfolio.buyAsset(apple, 5);
        portfolio.buyAsset(oil, 5);
        
        portfolio.sellAsset(apple, 5);
        portfolio.sellAsset(oil, 5);

        Money finalCash = portfolio.getCashWallet().getMoney();
        assertEquals(9985.0, finalCash.getAmount());
    }

}