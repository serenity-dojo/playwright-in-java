package com.serenitydojo.playwright.tutorial;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.tutorial.pageobjects.LoginPage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class LoginTest extends PlaywrightBaseTest{

    Page page;
    LoginPage loginPage;

    @BeforeEach
    void setupPage() {
        page = browser.newPage();
        loginPage = new LoginPage(page);
        loginPage.open();
    }

    @Test
    void shouldDisplayErrorForInvalidLogin() {
        loginPage.login("standard_user","invalid_password");
        loginPage.checkThatErrorMessageContains("Username and password do not match any user in this service");
        assertThat(loginPage.getErrorMessageField()).containsText("Username and password do not match any user in this service");
        Assertions.assertThat(loginPage.getErrorMessage()).contains("Username and password do not match any user in this service");
    }

    @Test
    void passwordIsMandatory() {
        loginPage.login("standard_user","");
        loginPage.checkThatErrorMessageContains("Password is required");
    }
}
