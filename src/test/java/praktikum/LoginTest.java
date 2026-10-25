package praktikum;

import org.junit.Assert;
import org.junit.Test;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;

public class LoginTest extends BaseTest {

    // 1. Вход по кнопке «Войти в аккаунт» на главной
    @Test
    public void loginThroughMainButton() {
        // BaseTest уже открывает BASE_URL в методе setUp()
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButtonMain();

        Assert.assertTrue("Не произошло перенаправление на страницу входа", driver.getCurrentUrl().contains("login"));
    }

    // 2. Вход через кнопку «Личный кабинет»
    @Test
    public void loginThroughPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        Assert.assertTrue("Не произошло перенаправление на страницу входа", driver.getCurrentUrl().contains("login"));
    }

    // 3. Вход через кнопку в форме регистрации
    @Test
    public void loginThroughRegisterForm() {
        driver.get(BASE_URL + "register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        Assert.assertTrue("Не произошло перенаправление на страницу входа", driver.getCurrentUrl().contains("login"));
    }

    // 4. Вход через кнопку в форме восстановления пароля
    @Test
    public void loginThroughForgotPasswordForm() {
        driver.get(BASE_URL + "forgot-password");
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginButton();

        Assert.assertTrue("Не произошло перенаправление на страницу входа", driver.getCurrentUrl().contains("login"));
    }
}
