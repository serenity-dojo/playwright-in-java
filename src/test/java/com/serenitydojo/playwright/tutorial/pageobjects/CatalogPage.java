package com.serenitydojo.playwright.tutorial.pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CatalogPage {

    public static final String INVENTORY_ITEM_WITH_TEXT = ".inventory_item:has-text('%s')";
    private final Page page;
    private final Locator cartBadge;

    public CatalogPage(Page page) {
        this.page = page;
        this.cartBadge = page.locator(".shopping_cart_badge");
    }

    public void addToCart(String itemName) {
        Locator productBlock = page.locator(String.format(INVENTORY_ITEM_WITH_TEXT, itemName));
        productBlock.locator("text=Add to cart").click();
    }

    public CartPage openShoppingCart() {
        cartBadge.click();
        return new CartPage(page);
    }
}
