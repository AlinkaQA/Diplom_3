package ru.yandex.prakticum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPage {
    private final WebDriver driver;

    private final By loginLink = By.linkText("Войти");

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход по ссылке 'Войти' со страницы восстановления пароля")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
