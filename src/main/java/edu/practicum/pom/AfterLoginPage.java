package edu.practicum.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AfterLoginPage {
    private final WebDriver driver;
    //локатор для кнопки Оформить заказ
    private final By makeOrderButton = By.xpath("//button[contains(text(),'Оформить заказ')]");
    //локатор для шапки страницы
    private final By header = By.xpath("//a[@class='active']//*[name()='svg']");
    //локатор для кнопки Личный кабинет
    private final By personalAccount = By.xpath("//p[contains(text(),'Личный Кабинет')]");

    public AfterLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    //Проверка отображения кнопки Оформить заказ
    public boolean checkIsVisibleMakeOrderButton() {
        List<WebElement> list = driver.findElements(makeOrderButton);
        return !list.isEmpty();
    }

    //метод кликает на кнопку Личный кабинет
    public void clickPersonalAccountButton() {
        Assert.assertTrue("Кнопка Личный кабинет не доступна", driver.findElement(personalAccount).isEnabled());
        driver.findElement(personalAccount).click();
    }
}
