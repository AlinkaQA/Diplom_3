package ru.yandex.prakticum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By loginHeader = By.xpath(".//h2[text()='Вход']");
    private final By emailField = By.xpath("//input[@name='name' or @type='text']");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.linkText("Зарегистрироваться");
    private final By forgotPasswordLink = By.linkText("Восстановить пароль");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидаем загрузку страницы логина")
    public void waitForLoginPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> d.findElement(loginHeader).isDisplayed());
    }

    @Step("Вводим email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Вводим пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Переходим по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Переходим по ссылке 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }
}
