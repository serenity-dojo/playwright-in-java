package com.serenitydojo.playwright.tutorial.pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private final Page page;

    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameField = page.locator("#user-name");
        this.passwordField = page.locator("#password");
        this.loginButton = page.locator("text=Login");
        this.errorMessage = page.locator("[data-test=error]");
    }

    public void open() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        usernameField.fill(username);
        passwordField.fill(password);
        loginButton.click();
    }

    public void checkThatErrorMessageContains(String expectedMessage) {
        assertThat(page.locator("[data-test=error]")).containsText(expectedMessage);
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }

    public Locator getErrorMessageField() {
        return errorMessage;
    }
}
