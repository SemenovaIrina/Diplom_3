package edu.practicum.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage {
    private final WebDriver driver;
    //локатор для ссылки Войти
    private final By LoginRef = By.xpath("//a[contains(text(),'Войти')]");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(LoginRef));
    }

    //метод кликает на ссылку Войти
    public void clickLoginRef() {
        Assert.assertTrue("Ссылка Войти не доступна", driver.findElement(LoginRef).isEnabled());
        //прокручиваем страницу до нужного элемента
        WebElement item = driver.findElement(LoginRef);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", item);
        driver.findElement(LoginRef).click();
    }
}
