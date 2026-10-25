package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By emailField = By.xpath(".//input[@name='name']");
    private final By passwordField = By.xpath(".//input[@type='password']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By restorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email '{0}', пароль '{1}' и нажать кнопку «Войти»")
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Кликнуть на ссылку «Зарегистрироваться»")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Кликнуть на ссылку «Восстановить пароль»")
    public void clickRestorePasswordLink() {
        driver.findElement(restorePasswordLink).click();
    }
}
