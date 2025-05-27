package ru.yandex.prakticum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.prakticum.constant.Endpoints;
import ru.yandex.prakticum.constant.SectionName;
import ru.yandex.prakticum.page.MainPage;

import static org.junit.Assert.assertTrue;

/**
 * Параметризованный тест конструктора
 */
@RunWith(Parameterized.class)
public class ConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;
    private final SectionName sectionName;

    public ConstructorTest(SectionName sectionName) {
        this.sectionName = sectionName;
    }

    @Parameterized.Parameters(name = "Секция конструктора: {0}")
    public static Object[][] data() {
        return new Object[][]{{SectionName.BUN}, {SectionName.SAUCE}, {SectionName.FILLING}};
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.get(Endpoints.BASE_URI);
        mainPage.waitForConstructorToLoad();
    }

    @After
    public void tearDown() { driver.quit(); }

    @Test
    @DisplayName("Переход по разделам конструктора")
    @Description("Проверяет, что вкладка активируется при клике")
    public void shouldSwitchConstructorSection() {
        if (sectionName == SectionName.BUN) mainPage.clickSection(SectionName.SAUCE);
        else mainPage.clickSection(SectionName.BUN);

        mainPage.clickSection(sectionName);
        String cls = mainPage.getClassName(sectionName);
        assertTrue("Секция не активна: " + sectionName, cls.contains("tab_tab_type_current__2BEPc"));
    }
}
