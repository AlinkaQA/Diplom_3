package ru.yandex.prakticum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.prakticum.constant.SectionName;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    private final By personalAccountButton = By.xpath("//a[@href='/account']");
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By constructorHeader = By.xpath(".//h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем кнопку 'Войти в аккаунт' на главной странице")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Нажимаем кнопку 'Личный кабинет'")
    public void clickPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Ожидаем появление конструктора на главной")
    public void waitForConstructorToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorHeader));
    }

    @Step("Кликаем по вкладке конструктора: {section}")
    public void clickSection(SectionName section) {
        By tab = getSectionLocator(section);
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(tab));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        // ожидаем, пока вкладка получит класс активности
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(tab, "class", "tab_tab_type_current"));
    }

    @Step("Получаем class атрибут вкладки: {section}")
    public String getClassName(SectionName section) {
        By tab = getSectionLocator(section);
        return driver.findElement(tab).getAttribute("class");
    }

    private By getSectionLocator(SectionName section) {
        switch (section) {
            case BUN:
                return By.xpath("//span[text()='Булки']/parent::div");
            case SAUCE:
                return By.xpath("//span[text()='Соусы']/parent::div");
            case FILLING:
                return By.xpath("//span[text()='Начинки']/parent::div");
            default:
                throw new IllegalArgumentException("Неизвестный раздел конструктора: " + section);
        }
    }
}


