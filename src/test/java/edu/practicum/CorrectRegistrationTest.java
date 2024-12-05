package edu.practicum;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static edu.practicum.utils.UtilsForDataPrepare.emailRandom;
import static edu.practicum.utils.UtilsForDataPrepare.stringRandomGenerate;

public class CorrectRegistrationTest extends PrepareUtilsAndSteps {

    @Test
    @Description("Checking the possibility of registration via the Sign in account button")
    public void registrationByClickOnLoginButton() {
        //так как предполагается потоковый запуск тестов, то у каждого теста должны быть свои данный для user
        //генерируем данные пользователя для регистрации
        Random rnd = new Random();
        String name = stringRandomGenerate(rnd.nextInt(254) + 1);
        String email = emailRandom(rnd.nextInt(254) + 1);
        String password = stringRandomGenerate(rnd.nextInt(248) + 7);
        //клик по кнопке Войти в аккаунт
        homePage.clickAccountLoginButton();
        //все действия, которые должны произойти после
        registrationSteps(name, email, password);
        //ждем пока загрузится страница Авторизации
        authPage.waitForLoadPage();
        Assert.assertTrue("Перехода на форму для входа в аккаунт не происходит", authPage.checkIsVisibleLoginFrame());
    }

    @Test
    @Description("Checking the possibility of registration via the Personal account button")
    public void registrationByClickOnPersonalAccount() {
        //так как предполагается потоковый запуск тестов, то у каждого теста должны быть свои данный для user
        //генерируем данные пользователя для регистрации
        Random rnd = new Random();
        String name = stringRandomGenerate(rnd.nextInt(254) + 1);
        String email = emailRandom(rnd.nextInt(254) + 1);
        String password = stringRandomGenerate(rnd.nextInt(248) + 7);
        //клик по кнопке Личный кабинет вверху страницы
        homePage.clickPersonalAccountButton();
        //все действия, которые должны произойти после
        registrationSteps(name, email, password);
        //ждем пока загрузится страница Авторизации
        authPage.waitForLoadPage();
        Assert.assertTrue("Перехода на форму для входа в аккаунт не происходит", authPage.checkIsVisibleLoginFrame());
    }
}
