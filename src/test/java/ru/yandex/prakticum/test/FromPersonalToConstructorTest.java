package ru.yandex.prakticum.test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.prakticum.constant.ButtonNameForConstructor;
import ru.yandex.prakticum.page.ProfilePage;
import ru.yandex.prakticum.constant.Endpoints;


import static org.junit.Assert.assertEquals;

/**
 * Тест перехода из Личного кабинета в Конструктор
 */
@RunWith(Parameterized.class)
public class FromPersonalToConstructorTest extends BaseTest {
    private final ButtonNameForConstructor buttonName;

    public FromPersonalToConstructorTest(ButtonNameForConstructor buttonName) {
        this.buttonName = buttonName;
    }

    @Parameterized.Parameters(name = "Кнопка перехода: {0}")
    public static Object[][] data() {
        return new Object[][]{{ButtonNameForConstructor.CONSTRUCTOR}, {ButtonNameForConstructor.LOGO_STELLAR_BURGER}};
    }

    @Test
    @DisplayName("Переход из ЛК в Конструктор по кнопкам")
    @Description("В ЛК нажимаем кнопку и возвращаемся в Конструктор")
    public void shouldGoToConstructorFromPersonalAccount() {
        loginAndGoToPersonal();
        ProfilePage p = new ProfilePage(driver);
        p.waitForProfilePageToLoad();
        p.changeButton(buttonName);
        assertEquals(Endpoints.BASE_URI + "/", driver.getCurrentUrl());
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