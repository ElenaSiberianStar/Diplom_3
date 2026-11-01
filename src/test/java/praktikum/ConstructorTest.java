package praktikum;

import org.junit.Assert;
import org.junit.Test;
import praktikum.pages.MainPage;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class ConstructorTest extends BaseTest {

    // Убран driver.get(BASE_URL) из каждого теста

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    @Description("Проверяем успешный переход и активацию вкладки «Соусы» в конструкторе")
        public void constructorTabTransitionToSauces() {

        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(BASE_URL);
        mainPage.clickSauceTab();
        Assert.assertTrue(mainPage.isTabActive("Соусы"));
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    @Description("Проверяем успешный переход и активацию вкладки «Начинки» в конструкторе")
    public void constructorTabTransitionToFillings() {

        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(BASE_URL);
        mainPage.clickFillingTab();
        Assert.assertTrue(mainPage.isTabActive("Начинки"));
    }

    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    @Description("Проверяем успешный переход и активацию вкладки «Булки» после переключения из другого раздела")
    public void constructorTabTransitionToBuns() {

        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(BASE_URL);
        // Сначала перейдем на соусы, а потом обратно на булки
        mainPage.clickSauceTab();
        mainPage.clickBunTab();
        Assert.assertTrue(mainPage.isTabActive("Булки"));
    }
}
