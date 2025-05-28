package ru.yandex.prakticum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.prakticum.constant.SectionName;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // — Все локаторы вынесены в поля класса —
    private final By personalAccountButton = By.xpath("//a[@href='/account']");
    private final By loginButton           = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By constructorHeader     = By.xpath(".//h1[text()='Соберите бургер']");
    private final By bunsTab               = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab             = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab           = By.xpath("//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем кнопку 'Войти в аккаунт' на главной странице")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    @Step("Нажимаем кнопку 'Личный кабинет'")
    public void clickPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(personalAccountButton))
                .click();
    }

    @Step("Ожидаем появление конструктора на главной")
    public void waitForConstructorToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorHeader));
    }

    @Step("Кликаем по вкладке конструктора: {section}")
    public void clickSection(SectionName section) {
        By tabLocator = getSectionLocator(section);
        WebElement tabElement = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(tabLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tabElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabElement);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
    }

    @Step("Получаем class атрибут вкладки: {section}")
    public String getClassName(SectionName section) {
        return driver.findElement(getSectionLocator(section)).getAttribute("class");
    }

    private By getSectionLocator(SectionName section) {
        switch (section) {
            case BUN:     return bunsTab;
            case SAUCE:   return saucesTab;
            case FILLING: return fillingsTab;
            default:
                throw new IllegalArgumentException("Неизвестный раздел конструктора: " + section);
        }
    }
}


