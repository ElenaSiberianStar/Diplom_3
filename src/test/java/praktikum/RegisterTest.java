package praktikum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.UserClient;
import praktikum.model.User;
import praktikum.pages.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;


public class RegisterTest extends BaseTest {
    private UserClient userClient;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUpUserData() {
        userClient = new UserClient();

        // Генерируем уникальный email для каждого запуска теста
        long timestamp = System.currentTimeMillis();
        email = "yagoda_test_" + timestamp + "@yandex.ru";
        password = "password123";
        name = "Ягодка";
    }

    // Убран driver.get(BASE_URL) из каждого теста
    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверяем перенаправление на страницу входа при успешной регистрации")
        public void userCanRegisterSuccessfully() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.openRegisterPage(BASE_URL);
        registerPage.register(name, email, password);

        Assert.assertTrue("Не произошло перенаправление на страницу входа", registerPage.isLoginButtonDisplayed());
    }

    @Test
    @DisplayName("Появление сообщения об неверном пароле")
    @Description("Проверяем появление сообщения об ошибке при некорректном вводе пароля")
    public void cannotRegisterWithShortPassword() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.openRegisterPage(BASE_URL);
        registerPage.register(name, email, "123");


        Assert.assertTrue("Сообщение об ошибке некорректного пароля не отображается", registerPage.isPasswordErrorDisplayed());
    }

    @After
    public void tearDownUser() {
        // Удаляем пользователя через API после теста
        try {
            User user = new User(email, password, name);
            String accessToken = userClient.login(user)
                    .extract()
                    .path("accessToken");

            if (accessToken != null) {
                userClient.delete(accessToken);
            }
        } catch (Exception e) {

        }
        // Закрытие браузера (driver.quit) автоматически выполнится в BaseTest.tearDown()
    }
}