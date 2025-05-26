package ru.yandex.prakticum.test;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import ru.yandex.prakticum.api.UserClient;
import ru.yandex.prakticum.generator.UserDataFactory;
import ru.yandex.prakticum.model.UserData;

import static org.junit.Assert.assertEquals;

public class RegisterTest extends BaseTest {
    private static final String EXPECTED_LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    private static final String EXPECTED_ERROR_TEXT = "Некорректный пароль";

    private String tempToken;

    @Before
    public void initUser() {
        user = UserDataFactory.generateValidUser();
    }

    @After
    public void deleteCreatedUser() {
        // если токен ещё не получен — пробуем логин
        if (tempToken == null) {
            tempToken = userClient.login(user)
                    .extract()
                    .path("accessToken");
        }
        if (tempToken != null) {
            userClient.delete(tempToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя через UI")
    public void shouldRegisterSuccessfullyWithValidData() {
        goToRegisterPage();

        registerPage.setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickRegisterButton();

        loginPage.waitForLoginPageToLoad();

        String actualUrl = driver.getCurrentUrl();
        assertEquals(EXPECTED_LOGIN_URL, actualUrl);
    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем < 6 символов")
    public void shouldShowErrorForShortPassword() {
        goToRegisterPage();

        registerPage.setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword("123")
                .clickRegisterButton();

        String actualError = registerPage.getTextException();
        assertEquals(EXPECTED_ERROR_TEXT, actualError);
    }

    @Step("Открываем форму регистрации через Личный кабинет")
    private void goToRegisterPage() {
        mainPage.clickPersonalAccount();
        loginPage.waitForLoginPageToLoad();
        loginPage.clickRegisterLink();
    }
}
