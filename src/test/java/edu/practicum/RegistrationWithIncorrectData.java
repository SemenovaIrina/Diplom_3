package edu.practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static edu.practicum.data_utils.UtilsForDataPrepare.emailRandom;
import static edu.practicum.data_utils.UtilsForDataPrepare.stringRandomGenerate;

@RunWith(Parameterized.class)
public class RegistrationWithIncorrectData extends PrepareUtilsAndSteps {
    private final String name;
    private final String email;
    private final String password;

    public RegistrationWithIncorrectData(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][]{
                {stringRandomGenerate(10), emailRandom(10), stringRandomGenerate(4)},
                {stringRandomGenerate(10), emailRandom(10), stringRandomGenerate(5)}
        };
    }

    @Test
    public void registrationByClickOnLoginButton() {
        //клик по кнопке Войти в аккаунт
        homePage.clickAccountLoginButton();
        //все действия, которые должны произойти после
        RegistrationSteps(name, email, password);
        reistrationPage.checkIsVisibleUserExistMessage();
    }

    @Test
    public void registrationByClickOnPersonalAccount() {
        //клик по кнопке Личный кабинет вверху страницы
        homePage.clickPersonalAccountButton();
        //все действия, которые должны произойти после
        RegistrationSteps(name, email, password);
        reistrationPage.checkIsVisibleUserExistMessage();
    }

}
