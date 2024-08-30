package com.serenitydojo.playwright.tutorial;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomepageTest extends PlaywrightBaseTest {

    @Test
    void shouldDisplayTheHomePageTitle() {
        var page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
        Assertions.assertThat(page.title()).isEqualTo("Swag Labs");
    }

    @Test
    void shouldDisplayTheLoginFormOnTheHomePage() {
        var page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
        assertThat(page.getByPlaceholder("Username")).isVisible();
        assertThat(page.getByPlaceholder("Password")).isVisible();
        assertThat(page.getByText("Login")).isVisible();
    }
}
