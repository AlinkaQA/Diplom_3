package ru.yandex.prakticum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.prakticum.constant.ButtonNameForConstructor;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;

    private final By profileTab = By.xpath(".//a[@href='/account']");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructorLink = By.linkText("Конструктор");
    private final By logoLink = By.className("AppHeader_header__logo__2D0X2");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидаем загрузку страницы профиля")
    public void waitForProfilePageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileTab));
    }

    @Step("Нажимаем кнопку 'Выход'")
    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }

    @Step("Переход по кнопке 'Конструктор'")
    public void clickConstructor() {
        driver.findElement(constructorLink).click();
    }

    @Step("Кликаем по логотипу 'Stellar Burgers'")
    public void clickLogo() {
        driver.findElement(logoLink).click();
    }

    @Step("Переход по кнопке: {buttonName}")
    public void changeButton(ButtonNameForConstructor buttonName) {
        switch (buttonName) {
            case CONSTRUCTOR:
                clickConstructor();
                break;
            case LOGO_STELLAR_BURGER:
                clickLogo();
                break;
            default:
                throw new IllegalArgumentException("Неизвестная кнопка: " + buttonName);
        }
    }
}
