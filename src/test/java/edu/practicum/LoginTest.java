package edu.practicum;

import edu.practicum.data_utils.UserAPIClient;
import edu.practicum.models.User;
import edu.practicum.models.UserAfterCreate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Random;

import static edu.practicum.data_utils.UserAPIClient.checksCorrectCreateAndLoginUser;
import static edu.practicum.data_utils.UtilsForDataPrepare.emailRandom;
import static edu.practicum.data_utils.UtilsForDataPrepare.stringRandomGenerate;

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
    public void loginByClickOnLoginButton() {
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //авторизация
        //клик по кнопке Войти в аккаунт
        homePage.clickAccountLoginButton();
        //все действия, которые должны произойти после
        LoginSteps(user.getEmail(), user.getPassword());
        //ждем пока загрузится страница
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Логина не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @Test
    public void loginByClickOnPersonalAccountButton() {
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //авторизация
        //клик по кнопке Личный кабинет
        homePage.clickPersonalAccountButton();
        //все действия, которые должны произойти после
        LoginSteps(user.getEmail(), user.getPassword());
        //ждем пока загрузится страница
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Логина не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @Test
    public void loginFromRegistrationForm() {
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //авторизация
        //клик по кнопке Войти в аккаунт
        homePage.clickAccountLoginButton();
        //клик по ссылке Зарегистрироваться
        authPage.clickRegistrationRef();
        //клик по ссылке Войти
        reistrationPage.clickLoginRef();
        //все действия, которые должны произойти после
        LoginSteps(user.getEmail(), user.getPassword());
        //ждем пока загрузится страница
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Логина не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @Test
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
        LoginSteps(user.getEmail(), user.getPassword());
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
