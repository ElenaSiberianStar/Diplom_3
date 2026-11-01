package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import praktikum.client.UserClient;
import praktikum.model.User;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class LoginTest extends BaseTest {

    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        super.setUp(); // Открывает браузер (если настроено в BaseTest)
        userClient = new UserClient();

        // Создаем уникального пользователя для тестов логина
        user = User.builder()
                .email("test_user_yagadka_" + System.currentTimeMillis() + "@yandex.ru")
                .password("password123")
                .name("Ягодка")
                .build();

        userClient.create(user);
    }

    // Убран driver.get(BASE_URL) из каждого теста
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    @Description("Проверяем полный процесс логина через кнопку «Войти в аккаунт» на главной")
    public void loginThroughMainButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButtonMain();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Не произошло успешного входа в аккаунт", mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверяем полный процесс логина через кнопку «Личный кабинет» в хедере")
    public void loginThroughPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Не произошло успешного входа в аккаунт", mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяем полный процесс логина по ссылке «Войти» на странице регистрации")
    public void loginThroughRegisterForm() {

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage(BASE_URL);
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("Не произошло успешного входа в аккаунт", mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяем полный процесс логина по ссылке «Войти» на странице восстановления пароля")
    public void loginThroughForgotPasswordForm() {

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openForgotPasswordPage(BASE_URL);
        forgotPasswordPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("Не произошло успешного входа в аккаунт", mainPage.isOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        // Удаляем тестового пользователя после каждого теста
        if (user != null) {
            try {
                accessToken = userClient.login(user)
                        .extract()
                        .path("accessToken");

                if (accessToken != null) {
                    userClient.delete(accessToken);
                }
            } catch (Exception e) {

            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
