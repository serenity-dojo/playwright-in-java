package com.serenitydojo.playwright.tutorial;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.tutorial.pageobjects.CartPage;
import com.serenitydojo.playwright.tutorial.pageobjects.CatalogPage;
import com.serenitydojo.playwright.tutorial.pageobjects.LoginPage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddToCartTest extends PlaywrightBaseTest {

    Page page;
    CatalogPage catalogPage;

    @BeforeEach
    void setupPage() {
        page = browser.newPage();
        LoginPage loginPage = new LoginPage(page);
        this.catalogPage = new CatalogPage(page);

        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
    }

    @AfterEach
    void closeBrowser() {
        page.close();
    }

    @Test
    void shouldAddAnItemToTheCart() {
        catalogPage.addToCart("Sauce Labs Bolt T-Shirt");
        List<String> itemTitles = catalogPage.openShoppingCart().getItemTitles();
        Assertions.assertThat(itemTitles).contains("Sauce Labs Bolt T-Shirt");
    }

    @Test
    void shouldAddTwoItemsToTheCart() {

        Locator productBlock1 = page.locator(".inventory_item:has-text('Sauce Labs Bolt T-Shirt')");
        Locator addToCartButton1 = productBlock1.locator("text=Add to cart");
        addToCartButton1.click();

        Locator productBlock2 = page.locator(".inventory_item:has-text('Sauce Labs Backpack')");
        Locator addToCartButton2 = productBlock2.locator("text=Add to cart");
        addToCartButton2.click();

        page.click(".shopping_cart_badge");
        Locator cartItem = page.locator(".cart_item");
        assertThat(cartItem.nth(0)).isVisible();
        assertThat(cartItem.nth(0)).containsText("Sauce Labs Bolt T-Shirt");
        assertThat(cartItem.nth(1)).isVisible();
        assertThat(cartItem.nth(1)).containsText("Sauce Labs Backpack");
    }

}
