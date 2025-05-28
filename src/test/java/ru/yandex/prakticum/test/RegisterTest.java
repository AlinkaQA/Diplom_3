package ru.yandex.prakticum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.prakticum.constant.Endpoints;
import ru.yandex.prakticum.generator.UserDataFactory;
import ru.yandex.prakticum.model.UserData;
import io.qameta.allure.Step;


import static org.junit.Assert.assertEquals;

/**
 * Тест регистрации пользователя
 */
public class RegisterTest extends BaseTest {
    private static final String EXPECTED_LOGIN_URL = Endpoints.BASE_URI + "/login";
    private static final String EXPECTED_ERROR_TEXT = "Некорректный пароль";
    private String tempToken;

    @Before
    public void initUser() { user = UserDataFactory.generateValidUser(); }

    @After
    public void deleteCreatedUser() {
        if (tempToken == null) {
            tempToken = userClient.login(user).extract().path("accessToken");
        }
        if (tempToken != null) userClient.delete(tempToken);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя через UI")
    @Description("Проверяет регистрацию и редирект на страницу логина")
    public void shouldRegisterSuccessfullyWithValidData() {
        goToRegisterPage();
        registerPage.setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickRegisterButton();
        loginPage.waitForLoginPageToLoad();
        assertEquals(EXPECTED_LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Ошибка при регистрации с паролем < 6 символов")
    @Description("Проверяет сообщение об ошибке при коротком пароле")
    public void shouldShowErrorForShortPassword() {
        goToRegisterPage();
        registerPage.setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword("123")
                .clickRegisterButton();
        String err = registerPage.getTextException();
        assertEquals(EXPECTED_ERROR_TEXT, err);
    }

    @Step("Открываем форму регистрации через Личный кабинет")
    private void goToRegisterPage() {
        mainPage.clickPersonalAccount();
        loginPage.waitForLoginPageToLoad();
        loginPage.clickRegisterLink();
    }
}