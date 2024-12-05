package edu.practicum;

import edu.practicum.models.User;
import edu.practicum.pom.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static edu.practicum.driver.WebDriverCreator.createWebDriver;

public class PrepareUtilsAndSteps {
    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    WebDriver driver;
    AuthorizationPage authPage;
    RegistrationPage registrationPage;
    AfterLoginPage afterLoginPage;
    HomePage homePage;
    PasswordRecoveryPage passwordRecoveryPage;
    PersonalAccountPage personalAccountPage;

    @Before
    public void init() {
        // Запускаем браузер, переходим на сайт
        driver = createWebDriver();
        driver.get(URL);
        authPage = new AuthorizationPage(driver);
        registrationPage = new RegistrationPage(driver);
        afterLoginPage = new AfterLoginPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        homePage = new HomePage(driver);
        //ждем когда прогрузится страница
        homePage.waitForLoadPage();
    }

    @After
    public void pageDown() {
        // Закрываем браузер
        driver.quit();
    }

    @Step
    @Description("Steps that you always need to follow when registering")
    public void registrationSteps(String name, String email, String password) {
        //попадаем на страницу для входа в аккаунт
        //ждем пока загрузится страница
        authPage.waitForLoadPage();
        //нажимаем на ссылку Зарегистрироваться
        authPage.clickRegistrationRef();
        //попадаем на страницу регистрации
        //ждем пока загрузится страница
        registrationPage.waitForLoadPage();
        //заполяем поля формы
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        //нажимаем кнопку Зарегистрироваться
        registrationPage.clickRegistrationButton();
    }

    @Step
    @Description("Steps that you always need to follow when login")
    public void loginSteps(String email, String password) {
        //попадаем на страницу для входа в аккаунт
        //ждем пока загрузится страница
        authPage.waitForLoadPage();
        //заполняем поля
        authPage.setEmail(email);
        authPage.setPassword(password);
        //нажимаем на кнопку Войти
        authPage.clickLoginButton();
    }

    @Step
    @Description("Steps that you always need to follow when checking the possibility of switching to your personal account")
    public void checkPossibilityComeInPersonalAccount(User user) {
        //авторизация
        //клик по кнопке Личный кабинет
        homePage.clickPersonalAccountButton();
        //все действия, которые должны произойти после
        loginSteps(user.getEmail(), user.getPassword());
        //ждем пока загрузится страница
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Логина не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
        //клик по кнопке Личный кабинет
        afterLoginPage.clickPersonalAccountButton();
        //ждем пока загрузится страница
        personalAccountPage.waitForLoadPage();
        Assert.assertTrue("Перехода на страницу Личного кабинета не происходит", personalAccountPage.checkIsAvailablePersonalAccountPage());
    }
}
