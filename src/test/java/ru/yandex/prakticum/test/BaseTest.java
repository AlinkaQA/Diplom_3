package ru.yandex.prakticum.test;

import io.qameta.allure.Step;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.prakticum.api.UserClient;
import ru.yandex.prakticum.constant.LoginSource;
import ru.yandex.prakticum.generator.UserDataFactory;
import ru.yandex.prakticum.model.UserData;
import ru.yandex.prakticum.page.MainPage;
import ru.yandex.prakticum.page.LoginPage;
import ru.yandex.prakticum.page.RegisterPage;
import ru.yandex.prakticum.page.RecoveryPage;
import ru.yandex.prakticum.page.ProfilePage;

import java.io.File;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected RecoveryPage recoveryPage;
    protected ProfilePage profilePage;

    protected UserClient userClient;
    protected UserData user;
    protected String accessToken;

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        ChromeOptions options = new ChromeOptions();

        if ("yandex".equalsIgnoreCase(browser)) {
            String[] possiblePaths = {
                    "/Applications/Yandex.app/Contents/MacOS/Yandex",
                    "C:\\Users\\%USERNAME%\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe",
                    "/usr/bin/yandex-browser",
            };
            boolean found = false;
            for (String path : possiblePaths) {
                File binary = new File(path);
                if (binary.exists()) {
                    options.setBinary(binary);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new RuntimeException(
                        "Не удалось найти исполняемый файл Яндекс.Браузера. Установите браузер или укажите путь вручную."
                );
            }
        } else {
            WebDriverManager.chromedriver().setup();
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        mainPage     = new MainPage(driver);
        loginPage    = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        recoveryPage = new RecoveryPage(driver);
        profilePage  = new ProfilePage(driver);

        userClient = new UserClient();

        if (!(this instanceof RegisterTest)) {
            user = UserDataFactory.generateValidUser();
            accessToken = userClient.create(user)
                    .extract()
                    .path("accessToken");
        }
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Открытие формы логина через: {source}")
    public void openLoginForm(LoginSource source) {
        switch (source) {
            case HOME_PAGE:
                mainPage.clickLoginButton();
                break;
            case PERSONAL_ACCOUNT:
                mainPage.clickPersonalAccount();
                break;
            case REGISTER_FORM:
                mainPage.clickPersonalAccount();
                loginPage.waitForLoginPageToLoad();
                loginPage.clickRegisterLink();
                registerPage.clickLoginLink();
                break;
            case RECOVERY_FORM:
                mainPage.clickPersonalAccount();
                loginPage.waitForLoginPageToLoad();
                loginPage.clickForgotPasswordLink();
                recoveryPage.clickLoginLink();
                break;
        }
    }
}
