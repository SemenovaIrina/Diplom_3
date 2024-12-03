package edu.practicum.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PersonalAccountPage {
    private final WebDriver driver;
    //локатор для блока с личной информацией
    private final By profile = By.className("Account_account__vgk_w");
    //локатор для кнопки Конструктор
    private final By constructor = By.xpath("//p[contains(text(),'Конструктор')]");
    //локатор для логотипа
    private final By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']//a//*[name()='svg']");
    //локатор для кнопки Выход
    private final By logoutButton = By.xpath("//button[contains(text(),'Выход')]");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(profile));
    }

    //Проверка перехода на страницу личного кабинета
    public boolean checkIsAvailablePersonalAccountPage() {
        List<WebElement> list = driver.findElements(profile);
        return !list.isEmpty();
    }

    //метод кликает на кнопку Конструктор
    public void clickConstructorButton() {
        Assert.assertTrue("Кнопка Конструктор не доступна", driver.findElement(constructor).isEnabled());
        driver.findElement(constructor).click();
    }

    //метод кликает на логотип
    public void clickLogo() {
        Assert.assertTrue("Логотип не доступен", driver.findElement(logo).isEnabled());
        driver.findElement(logo).click();
    }

    //метод кликает на кнопку Выход
    public void clickLogoutButton() {
        Assert.assertTrue("Кнопка Выход не доступна", driver.findElement(logoutButton).isEnabled());
        driver.findElement(logoutButton).click();
    }
}
