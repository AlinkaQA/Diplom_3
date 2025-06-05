package ru.yandex.prakticum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.prakticum.constant.LoginSource;
import ru.yandex.prakticum.constant.Endpoints;


import static org.junit.Assert.assertEquals;

/**
 * Тест авторизации из разных точек
 */
@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {
    private final LoginSource source;

    public LoginTest(LoginSource source) { this.source = source; }

    @Parameterized.Parameters(name = "Точка входа: {0}")
    public static Object[][] data() {
        return new Object[][]{{LoginSource.HOME_PAGE}, {LoginSource.PERSONAL_ACCOUNT}, {LoginSource.REGISTER_FORM}, {LoginSource.RECOVERY_FORM}};
    }

    @Test
    @DisplayName("Авторизация пользователя с разных точек входа")
    @Description("Проверка успешного входа в систему")
    public void loginFromDifferentSourcesTest() {
        openLoginForm(source);
        loginPage.waitForLoginPageToLoad();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();
        mainPage.waitForConstructorToLoad();
        assertEquals(Endpoints.BASE_URI + "/", driver.getCurrentUrl());
    }
}