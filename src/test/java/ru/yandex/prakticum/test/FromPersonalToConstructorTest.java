package ru.yandex.prakticum.test;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.prakticum.constant.ButtonNameForConstructor;
import ru.yandex.prakticum.page.ProfilePage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.prakticum.constant.ButtonNameForConstructor.CONSTRUCTOR;
import static ru.yandex.prakticum.constant.ButtonNameForConstructor.LOGO_STELLAR_BURGER;

@RunWith(Parameterized.class)
public class FromPersonalToConstructorTest extends BaseTest {

    private final ButtonNameForConstructor buttonName;

    public FromPersonalToConstructorTest(ButtonNameForConstructor buttonName) {
        this.buttonName = buttonName;
    }

    @Parameterized.Parameters(name = "Переход по кнопке: {0}")
    public static Object[][] getButtons() {
        return new Object[][]{
                {CONSTRUCTOR},
                {LOGO_STELLAR_BURGER}
        };
    }

    @Test
    @DisplayName("Переход из ЛК в Конструктор по кнопкам")
    public void shouldGoToConstructorFromPersonalAccount() {
        loginAndGoToPersonalAccount();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForProfilePageToLoad();
        profilePage.changeButton(buttonName); // теперь метод есть в ProfilePage

        assertEquals(getExpectedConstructorUrl(), driver.getCurrentUrl());
    }

    @Step("Авторизуемся и переходим в ЛК")
    private void loginAndGoToPersonalAccount() {
        mainPage.clickPersonalAccount(); // первый клик
        loginPage.waitForLoginPageToLoad();
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccount(); // второй клик — переход в профиль
    }


    private String getExpectedConstructorUrl() {
        return "https://stellarburgers.nomoreparties.site/";
    }
}
