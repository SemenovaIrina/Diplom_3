package edu.practicum;

import edu.practicum.utils.UserAPIClient;
import edu.practicum.models.User;
import edu.practicum.models.UserAfterCreate;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Random;

import static edu.practicum.utils.UserAPIClient.checksCorrectCreateAndLoginUser;
import static edu.practicum.utils.UtilsForDataPrepare.emailRandom;
import static edu.practicum.utils.UtilsForDataPrepare.stringRandomGenerate;

@RunWith(Parameterized.class)
public class LoginTest extends PrepareUtilsAndSteps {
    UserAfterCreate userAfterCreate;
    User user;

    public LoginTest(User user) {
        this.user = user;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getUser() {
        Random rnd = new Random();
        //создаем пользователя через API
        User user = new User.Builder()
                .email(emailRandom(rnd.nextInt(254) + 1))
                .password(stringRandomGenerate(rnd.nextInt(248) + 6))
                .name(stringRandomGenerate(rnd.nextInt(254) + 1))
                .build();
        return new Object[][]{
                {user}
        };
    }

    @Test
    @Description("Checking the possibility of login via the Login button")
    public void loginByClickOnLoginButton() {
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //авторизация
        //клик по кнопке Войти в аккаунт
        homePage.clickAccountLoginButton();
        //все действия, которые должны произойти после
        loginSteps(user.getEmail(), user.getPassword());
        //ждем пока загрузится страница
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Логина не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @Test
    @Description("Checking the possibility of login via the Personal account button")
    public void loginByClickOnPersonalAccountButton() {
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //авторизация
        //клик по кнопке Личный кабинет
        homePage.clickPersonalAccountButton();
        //все действия, которые должны произойти после
        loginSteps(user.getEmail(), user.getPassword());
        //ждем пока загрузится страница
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Логина не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @Test
    @Description("Checking the possibility of login from Registration form")
    public void loginFromRegistrationForm() {
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //авторизация
        //клик по кнопке Войти в аккаунт
        homePage.clickAccountLoginButton();
        //клик по ссылке Зарегистрироваться
        authPage.clickRegistrationRef();
        //клик по ссылке Войти
        registrationPage.clickLoginRef();
        //все действия, которые должны произойти после
        loginSteps(user.getEmail(), user.getPassword());
        //ждем пока загрузится страница
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Логина не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @Test
    @Description("Checking the possibility of login from Password recovery Form")
    public void loginFromPasswordRecovery() {
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //авторизация
        //клик по кнопке Войти в аккаунт
        homePage.clickAccountLoginButton();
        //клик по ссылке Восстановить пароль
        authPage.clickPasswordRecoveryRef();
        //клик по ссылке Войти
        passwordRecoveryPage.clickLoginRef();
        //все действия, которые должны произойти после
        loginSteps(user.getEmail(), user.getPassword());
        //ждем пока загрузится страница
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Логина не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @After
    public void tearDown() {
        // удаляем созданного пользователя
        UserAPIClient.addBearerTokenInHeader(userAfterCreate.getAccessToken());
        UserAPIClient.delete();
    }
}
