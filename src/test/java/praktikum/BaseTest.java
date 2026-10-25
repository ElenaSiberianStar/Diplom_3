package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser");

        if ("yandex".equalsIgnoreCase(browser)) {
            // Настройка для Яндекс.Браузера
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else {
            // По умолчанию запускаем Chrome
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Сразу открываем главную страницу при старте каждого теста
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
