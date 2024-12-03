package edu.practicum.page_objects.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    //локатор для поля Имя
    private final By name = By.xpath("(//input[@name='name'])[1]");
    //локатор для поля Email
    private final By email = By.xpath("(//input[@name='name'])[2]");
    //локатор для поля Пароль
    private final By password = By.xpath("(//input[@name='Пароль'])[1]");
    //локатор для кнопки Зарегистрироваться
    private final By registrationButton = By.xpath("(//button[contains(text(),'Зарегистрироваться')])[1]");
    //локатор для ссылки Войти
    private final By LoginRef = By.xpath("(//a[contains(text(),'Войти')])");
    //локатор для надпипи "Такой пользователь уже существует"
    private final By userExist = By.xpath("//p[@class='input__error text_type_main-default']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
    }

    //метод вводит значение в поле Имя
    public void setName(String newName) {
        Assert.assertTrue("Поле для ввода имени не доступно", driver.findElement(name).isEnabled());
        driver.findElement(name).sendKeys(newName);
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

    //метод кликает на кнопку Зарегистрироваться
    public void clickRegistrationButton() {
        Assert.assertTrue("Кнопка Зарегистрироваться не доступна", driver.findElement(registrationButton).isEnabled());
        driver.findElement(registrationButton).click();
    }

    //метод кликает на ссылку Войти
    public void clickLoginRef() {
        Assert.assertTrue("Ссылка Войти не доступна", driver.findElement(LoginRef).isEnabled());
        //прокручиваем страницу до нужного элемента
        WebElement item = driver.findElement(LoginRef);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", item);
        driver.findElement(LoginRef).click();
    }

    //Проверка отображения надписи Такой пользователь уже существует
    public void checkIsVisibleUserExistMessage() {
        Assert.assertTrue("Сообщение о том, что такой пользователь существует не доступно", driver.findElement(userExist).isEnabled());
    }
}
