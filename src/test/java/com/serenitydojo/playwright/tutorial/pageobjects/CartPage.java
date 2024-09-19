package com.serenitydojo.playwright.tutorial.pageobjects;

import com.microsoft.playwright.Page;

import java.util.List;

public class CartPage {

    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public List<String> getItemTitles() {
        return page.locator(".inventory_item_name").allTextContents();
    }
}
