package praktikum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.client.UserClient;
import praktikum.model.User;
import praktikum.pages.RegisterPage;

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

    @Test
    public void userCanRegisterSuccessfully() {
        RegisterPage registerPage = new RegisterPage(driver);

        driver.get(BASE_URL + "register");
        registerPage.register(name, email, password);

        // Ждем до 10 секунд, пока URL не изменится на страницу входа
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("login"));

        // Проверяем перенаправление на страницу входа
        Assert.assertTrue("Не произошло перенаправление на страницу входа", driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void cannotRegisterWithShortPassword() {
        RegisterPage registerPage = new RegisterPage(driver);

        driver.get(BASE_URL + "register");
        registerPage.register(name, email, "123");

        // Проверяем появление ошибки о некорректном пароле
        Assert.assertTrue("Сообщение об ошибке некорректного пароля не отображается", registerPage.isPasswordErrorDisplayed());
    }

    @After
    public void tearDownUser() {
        // Удаляем пользователя через API после теста (требование диплома)
        try {
            User user = new User(email, password, name);
            String accessToken = userClient.login(user)
                    .extract()
                    .path("accessToken");

            if (accessToken != null) {
                userClient.delete(accessToken);
            }
        } catch (Exception e) {
            // Если пользователь не был создан (например, в негативном тесте), просто идем дальше
        }
        // Закрытие браузера (driver.quit) автоматически выполнится в BaseTest.tearDown()
    }
}