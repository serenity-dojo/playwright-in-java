package com.serenitydojo.playwright.tutorial;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class PlaywrightBaseTest {

    static Playwright playwright;
    static Browser browser;

    @BeforeAll
    static void setupPlaywright() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closePlaywright() {
        playwright.close();
    }
}
