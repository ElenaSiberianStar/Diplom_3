package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    // Локаторы по тексту
    private final By loginButtonMain = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    // Вкладки конструктора
    private final By bunTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By sauceTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingTab = By.xpath(".//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на кнопку «Войти в аккаунт» на главной странице")
    public void clickLoginButtonMain() {
        driver.findElement(loginButtonMain).click();
    }

    @Step("Нажать на кнопку «Личный Кабинет»")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Кликнуть на вкладку «Булки»")
    public void clickBunTab() {
        driver.findElement(bunTab).click();
    }

    @Step("Кликнуть на вкладку «Соусы»")
    public void clickSauceTab() {
        driver.findElement(sauceTab).click();
    }

    @Step("Кликнуть на вкладку «Начинки»")
    public void clickFillingTab() {
        driver.findElement(fillingTab).click();
    }

    @Step("Проверить, что вкладка '{0}' активна")
    public boolean isTabActive(String tabName) {
        By tab = By.xpath(".//span[text()='" + tabName + "']/parent::div");
        return driver.findElement(tab).getAttribute("class").contains("tab_tab_type_current");
    }
}
