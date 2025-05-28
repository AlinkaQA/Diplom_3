package ru.yandex.prakticum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import io.qameta.allure.Step;


import static org.junit.Assert.assertTrue;

/**
 * Тест выхода из аккаунта
 */
public class LogoutTest extends BaseTest {
    @Test
    @DisplayName("Выход из аккаунта из ЛК")
    @Description("Проверяет редирект на /login после выхода")
    public void shouldLogoutSuccessfully() {
        loginAndGoToPersonal();
        profilePage.waitForProfilePageToLoad();
        profilePage.clickLogout();
        loginPage.waitForLoginPageToLoad();
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Step("Авторизация и переход в ЛК")
    private void loginAndGoToPersonal() {
        mainPage.clickPersonalAccount();
        loginPage.waitForLoginPageToLoad();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
        mainPage.clickPersonalAccount();
    }
}