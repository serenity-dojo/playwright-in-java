package com.serenitydojo.playwright.tutorial;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class LoginTest extends PlaywrightBaseTest{
    @Test
    void shouldDisplayErrorForInvalidLogin() {
        Page page = browser.newPage();

        page.navigate("https://www.saucedemo.com/");

        Locator inputFields = page.locator(".form_input");

        Locator nameField = inputFields.first();
        Locator passwordField = inputFields.nth(1);

        Locator loginBox = page.locator(".login-box");
        Locator loginButton = loginBox.locator("[type=submit]");

        nameField.fill("standard_user");
        passwordField.fill("invalid_password");
        loginButton.click();

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
