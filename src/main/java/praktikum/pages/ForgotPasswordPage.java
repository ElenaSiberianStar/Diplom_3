package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    // Локатор кнопки «Войти»
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на кнопку «Войти» на странице восстановления пароля")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Открыть страницу восстановления пароля")
    public void openForgotPasswordPage(String baseUrl) {
        driver.get(baseUrl + "forgot-password");
    }
}
