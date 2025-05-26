package ru.yandex.prakticum.test;

import io.qameta.allure.junit4.DisplayName;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.prakticum.constant.SectionName;
import ru.yandex.prakticum.page.MainPage;

import static org.junit.Assert.assertTrue;
import static ru.yandex.prakticum.constant.SectionName.*;

@Slf4j
@RunWith(Parameterized.class)
public class ConstructorTest {

    private static final String SITE = "https://stellarburgers.nomoreparties.site";
    private WebDriver driver;
    private MainPage mainPage;
    private final SectionName sectionName;

    public ConstructorTest(SectionName sectionName) {
        this.sectionName = sectionName;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {BUN},
                {SAUCE},
                {FILLING}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.get(SITE);
        mainPage.waitForConstructorToLoad();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход по разделам конструктора")
    public void shouldSwitchConstructorSection() {
        // Если секция уже активна (например, Булки по умолчанию), переключаемся на другую, чтобы сбросить состояние
        if (sectionName == BUN) {
            mainPage.clickSection(SAUCE);
        } else {
            mainPage.clickSection(BUN);
        }

        // Теперь кликаем по нужной секции
        mainPage.clickSection(sectionName);

        // Получаем класс и проверяем, что таб стал активным
        String actualClass = mainPage.getClassName(sectionName);
        assertTrue("Секция не стала активной: " + sectionName,
                actualClass.contains("tab_tab_type_current__2BEPc"));
    }
}
