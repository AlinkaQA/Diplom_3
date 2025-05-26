package ru.yandex.prakticum.test;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PersonalAccountTest extends BaseTest {

    @Test
    @DisplayName("Переход в Личный кабинет авторизованным пользователем")
    public void shouldGoToPersonalAccountAfterLogin() {
        login();

        mainPage.clickPersonalAccount(); // второй клик — переход в ЛК

        profilePage.waitForProfilePageToLoad();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("URL не содержит '/account'", currentUrl.contains("/account"));
    }

    @Step("Авторизация пользователя")
    private void login() {
        mainPage.clickPersonalAccount();
        loginPage.waitForLoginPageToLoad();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
    }
}
