package ru.yandex.prakticum.test;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Выход из аккаунта из личного кабинета")
    public void shouldLogoutSuccessfully() {
        loginAndGoToPersonalAccount();

        profilePage.waitForProfilePageToLoad();
        profilePage.clickLogout();

        loginPage.waitForLoginPageToLoad();

        assertTrue("После выхода URL должен содержать /login",
                driver.getCurrentUrl().contains("/login"));
    }

    @Step("Авторизация и переход в личный кабинет")
    private void loginAndGoToPersonalAccount() {
        mainPage.clickPersonalAccount();
        loginPage.waitForLoginPageToLoad();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickPersonalAccount();
    }
}
