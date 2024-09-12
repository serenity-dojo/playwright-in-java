package com.serenitydojo.playwright.tutorial;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddToCartTest extends PlaywrightBaseTest {

    Page page;

    @BeforeEach
    void login() {
        page = browser.newPage();

        page.navigate("https://www.saucedemo.com/");

        page.fill("#user-name","standard_user");
        page.fill("#password","secret_sauce");
        page.click("#login-button");
    }

    @AfterEach
    void closeBrowser() {
        page.close();
    }

    @Test
    void shouldAddAnItemToTheCart() {
        login();

        Locator productBlock = page.locator(".inventory_item:has-text('Sauce Labs Bolt T-Shirt')");
        Locator addToCartButton = productBlock.locator("text=Add to cart");
        addToCartButton.click();

        page.click(".shopping_cart_badge");
        Locator cartItem = page.locator(".cart_item");
        assertThat(cartItem).isVisible();
        assertThat(cartItem).containsText("Sauce Labs Bolt T-Shirt");
    }

    @Test
    void shouldAddTwoItemsToTheCart() {
        login();

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
