package edu.practicum;

import edu.practicum.page_objects.AuthorizationPage;
import edu.practicum.page_objects.RegistrationPage;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrepareUtils {
    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    WebDriver driver;
    AuthorizationPage authPage;
    RegistrationPage reistrationPage;

    @Before
    public void init() {
        // Запускаем браузер, переходим на сайт
        driver = new ChromeDriver();
        driver.get(URL);
        authPage = new AuthorizationPage(driver);
        reistrationPage = new RegistrationPage(driver);
    }

    @After
    public void pageDown() {
        // Закрываем браузер
        driver.quit();
    }
    @Step
    public void RegistrationSteps(String name, String email, String password) {
        //попадаем на страницу для входа в аккаунт
        //ждем пока загрузится страница
        authPage.waitForLoadPage();
        //нажимаем на ссылку Зарегистрироваться
        authPage.clickRegistrationRef();
        //попадаем на страницу регистрации
        reistrationPage = new RegistrationPage(driver);
        //ждем пока загрузится страница
        reistrationPage.waitForLoadPage();
        //заполяем поля формы
        reistrationPage.setName(name);
        reistrationPage.setEmail(email);
        reistrationPage.setPassword(password);
        //нажимаем кнопку Зарегистрироваться
        reistrationPage.clickRegistrationButton();
    }

}
