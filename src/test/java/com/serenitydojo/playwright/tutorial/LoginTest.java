package com.serenitydojo.playwright.tutorial;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Selectors;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class LoginTest extends PlaywrightBaseTest{
    @Test
    void shouldDisplayErrorForInvalidLogin() {
        Page page = browser.newPage();

        page.navigate("https://www.saucedemo.com/");

        page.fill("#user-name", "standard_user");
        page.fill("#password", "invalid_password");
        page.click("#login-button");

        assertThat(page.locator("[data-test=error]")).containsText("Username and password do not match any user in this service");
    }

    @Test
    void passwordIsMandatory() {
        Page page = browser.newPage();

        page.navigate("https://www.saucedemo.com/");

        page.fill("#user-name", "standard_user");
        page.click("#login-button");

        assertThat(page.locator("[data-test=error]")).containsText("Password is required");
    }
}
