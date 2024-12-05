package edu.practicum.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    //локатор для кнопки Личный кабинет
    private final By personalAccount = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    //локатор для кнопки Войти в аккаунт
    private final By LoginAccount = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    //локатор для ссылки на раздел Соусы в конструкторе
    private final By sauceRef = By.xpath("//span[contains(text(),'Соусы')]");
    //локатор для ссылки на раздел Соусы в конструкторе, когда он выбран
    private final By sauceRefWhenSelected = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[contains(text(),'Соусы')]");
    //локатор для ссылки на раздел Начинки в конструкторе
    private final By fillingRef = By.xpath("//span[contains(text(),'Начинки')]");
    //локатор для ссылки на раздел Начинки в конструкторе, когда он выбран
    private final By fillingRefWhenSelected = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[contains(text(),'Начинки')]");
    //локатор для ссылки на раздел Булки в конструкторе
    private final By bunRef = By.xpath("//span[contains(text(),'Булки')]");
    //локатор для ссылки на раздел Булки в конструкторе, когда он выбран
    private final By bunRefWhenSelected = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[contains(text(),'Булки')]");
    //локатор для заголовка раздела Соусы внутри конструктора

    //private final By sauceHeader = By.xpath("//h2[contains(text(),'Соусы')]");
    //локатор для заголовка раздела Булки внутри конструктора
    //private final By bunHeader = By.xpath("//h2[contains(text(),'Булки')]");
    //локатор для заголовка раздела Начинки внутри конструктора
    // private final By fillingHeader = By.xpath("//h2[contains(text(),'Начинки')]");
    //локатор для секции Соберите бургер с ингредиентами
    private final By burgerIngredients = By.className("BurgerIngredients_ingredients__1N8v2");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(burgerIngredients));
    }

    //метод кликает на кнопку Личный кабинет
    public void clickPersonalAccountButton() {
        Assert.assertTrue("Кнопка Личный кабинет не доступна", driver.findElement(personalAccount).isEnabled());
        driver.findElement(personalAccount).click();
    }

    //метод кликает на кнопку Войти в аккаунт
    public void clickAccountLoginButton() {
        Assert.assertTrue("Кнопка Войти в аккаунт не доступна", driver.findElement(LoginAccount).isEnabled());
        driver.findElement(LoginAccount).click();
    }

    //метод кликает на ссылку Соусы в конструкторе
    public void clickSauceRefInConstructor() {
        Assert.assertTrue("Раздел Соусы в конструкторе не доступен", driver.findElement(sauceRef).isEnabled());
        driver.findElement(sauceRef).click();
    }

    //метод кликает на ссылку Начинки в конструкторе
    public void clickFillingRefInConstructor() {
        Assert.assertTrue("Раздел Начинки в конструкторе не доступен", driver.findElement(fillingRef).isEnabled());
        driver.findElement(fillingRef).click();
    }

    //метод кликает на ссылку Булки в конструкторе
    public void clickBunRefInConstructor() {
        Assert.assertTrue("Раздел Булки в конструкторе не доступен", driver.findElement(bunRef).isEnabled());
        driver.findElement(bunRef).click();
    }

    //Проверка отображения раздела Соусы в конструкторе
    public boolean checkIsVisibleSauceSectionInConstructor() {
        return (driver.findElement(sauceRefWhenSelected).isEnabled());
    }

    //Проверка отображения раздела Начинки в конструкторе
    public boolean checkIsVisibleFillingSectionInConstructor() {
        return driver.findElement(fillingRefWhenSelected).isEnabled();
    }

    //Проверка отображения раздела Булки в конструкторе
    public boolean checkIsVisibleBunSectionInConstructor() {
        return driver.findElement(bunRefWhenSelected).isEnabled();
    }
}
