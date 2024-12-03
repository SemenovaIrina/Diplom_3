package edu.practicum.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AuthorizationPage {
    private final WebDriver driver;
    //локатор для ссылки Зарегистрироваться
    private final By RegistrationRef = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    //локатор для ссылки Восстановить пароль
    private final By passwordRecoveryRef = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    //локатор для поля Email
    private final By email = By.xpath(".//input[@name='name']");
    //локатор для поля Password
    private final By password = By.xpath(".//input[@name='Пароль']");
    //локатор для LoginFrame
    private final By loginFrame = By.xpath("//h2[contains(text(),'Вход')]");
    //локатор для кнопки Войти
    private final By loginButton = By.xpath("//button[contains(text(),'Войти')]");


    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(loginFrame));
    }

    //метод кликает на ссылку Зарегистрироваться
    public void clickRegistrationRef() {
        Assert.assertTrue("Ссылка Зарегистрироваться не доступна", driver.findElement(RegistrationRef).isEnabled());
        //прокручиваем страницу до нужного элемента
        WebElement item = driver.findElement(RegistrationRef);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", item);
        driver.findElement(RegistrationRef).click();
    }

    //метод кликает на ссылку Восстановить пароль
    public void clickPasswordRecoveryRef() {
        Assert.assertTrue("Ссылка Восстановить пароль не доступна", driver.findElement(passwordRecoveryRef).isEnabled());
        //прокручиваем страницу до нужного элемента
        WebElement item = driver.findElement(passwordRecoveryRef);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", item);
        driver.findElement(passwordRecoveryRef).click();
    }

    //Проверка доступности формы для входа в аккаунт
    public boolean checkIsVisibleLoginFrame() {
        List<WebElement> list = driver.findElements(loginFrame);
        return !list.isEmpty();
    }

    //метод вводит значение в поле Email
    public void setEmail(String newEmail) {
        Assert.assertTrue("Поле для ввода email не доступно", driver.findElement(email).isEnabled());
        driver.findElement(email).sendKeys(newEmail);
    }

    //метод вводит значение в поле Пароль
    public void setPassword(String newPassword) {
        Assert.assertTrue("Поле для ввода комментария не доступно", driver.findElement(password).isEnabled());
        driver.findElement(password).sendKeys(newPassword);
    }

    //метод кликает на кнопку Войти
    public void clickLoginButton() {
        Assert.assertTrue("Кнопка Войти не доступна", driver.findElement(loginButton).isEnabled());
        //прокручиваем страницу до нужного элемента
        WebElement item = driver.findElement(loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", item);
        driver.findElement(loginButton).click();
    }
}
