package praktikum;

import org.junit.Assert;
import org.junit.Test;
import praktikum.pages.MainPage;

public class ConstructorTest extends BaseTest {

    @Test
    // перейдем на соусы
    public void constructorTabTransitionToSauces() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceTab();
        Assert.assertTrue(mainPage.isTabActive("Соусы"));
    }

    @Test
    // перейдем на начинки
    public void constructorTabTransitionToFillings() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingTab();
        Assert.assertTrue(mainPage.isTabActive("Начинки"));
    }

    @Test
    // перейдем на булки
    public void constructorTabTransitionToBuns() {
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driver);
        // Сначала перейдем на соусы, а потом обратно на булки
        mainPage.clickSauceTab();
        mainPage.clickBunTab();
        Assert.assertTrue(mainPage.isTabActive("Булки"));
    }
}
