package ru.yandex.prakticum.test;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.prakticum.constant.LoginSource;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    private final LoginSource source;

    private static final String EXPECTED_URL = "https://stellarburgers.nomoreparties.site/";

    public LoginTest(LoginSource source) {
        this.source = source;
    }

    @Parameterized.Parameters(name = "Источник входа: {0}")
    public static Object[][] getSources() {
        return new Object[][]{
                {LoginSource.HOME_PAGE},
                {LoginSource.PERSONAL_ACCOUNT},
                {LoginSource.REGISTER_FORM},
                {LoginSource.RECOVERY_FORM}
        };
    }

    @Test
    @DisplayName("Авторизация пользователя с разных точек входа")
    public void loginFromDifferentSourcesTest() {
        openLoginForm(source);

        loginPage.waitForLoginPageToLoad();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();

        mainPage.waitForConstructorToLoad();
        String actualUrl = driver.getCurrentUrl();

        assertEquals(EXPECTED_URL, actualUrl);
    }
}
