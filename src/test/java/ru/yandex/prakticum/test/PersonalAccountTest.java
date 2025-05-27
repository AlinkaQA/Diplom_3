package ru.yandex.prakticum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import io.qameta.allure.Step;


import static org.junit.Assert.assertTrue;

/**
 * Тест перехода в Личный кабинет
 */
public class PersonalAccountTest extends BaseTest {
    @Test
    @DisplayName("Переход в ЛК авторизованным пользователем")
    @Description("Проверяет наличие /account в URL после входа и клика")
    public void shouldGoToPersonalAccountAfterLogin() {
        login();
        mainPage.clickPersonalAccount();
        profilePage.waitForProfilePageToLoad();
        assertTrue(driver.getCurrentUrl().contains("/account"));
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
