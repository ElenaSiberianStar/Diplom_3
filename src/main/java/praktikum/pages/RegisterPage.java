package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// Импортирую WebDriverWait и ExpectedConditions для работы с ожиданиями
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//input[@type='password']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    private final By loginButtonOnLoginPage = By.xpath(".//button[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        // Инициализирую WebDriverWait в конструкторе
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Открыть страницу регистрации")
    public void openRegisterPage(String baseUrl) {
        driver.get(baseUrl + "register");
    }

    @Step("Заполнить форму регистрации: имя '{0}', email '{1}', пароль '***' и нажать кнопку «Зарегистрироваться»")
    public void register(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    @Step("Проверить отображение ошибки о некорректном пароле")
    public boolean isPasswordErrorDisplayed() {
        return driver.findElement(passwordError).isDisplayed();
    }

    @Step("Дождаться появления страницы входа (по кнопке «Войти»)")
    public boolean isLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonOnLoginPage)).isDisplayed();
    }

    @Step("Кликнуть на ссылку «Войти» со страницы регистрации")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
