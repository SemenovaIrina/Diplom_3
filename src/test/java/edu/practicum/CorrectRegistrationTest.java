package edu.practicum;

import edu.practicum.page_objects.AuthorizationPage;
import edu.practicum.page_objects.HomePage;
import edu.practicum.page_objects.RegistrationPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static edu.practicum.page_objects.data.UtilsForDataPrepare.emailRandom;
import static edu.practicum.page_objects.data.UtilsForDataPrepare.stringRandomGenerate;

@RunWith(Parameterized.class)
public class RegistrationTest extends PrepareUtils {
    private final String name;
    private final String email;
    private final String password;

    public RegistrationTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][]{
                {stringRandomGenerate(10), emailRandom(10), stringRandomGenerate(10)}
        };
    }

    @Test
    public void  registrationByClickOnLoginButton() {
        HomePage page = new HomePage(driver);
        //ждем когда прогрузится страница
        page.waitForLoadPage();
        //клик по кнопке Войти в аккаунт
        page.clickAccountLoginButton();
        //все действия, которые должны произойти после
        RegistrationSteps();
    }

    @Test
    public void  registrationByClickOnPersonalAccount() {
        HomePage page = new HomePage(driver);
        //ждем когда прогрузится страница
        page.waitForLoadPage();
        //клик по кнопке Личный кабинет вверху страницы
        page.clickPersonalAccountButton();
        //все действия, которые должны произойти после
        RegistrationSteps();
    }

    public void RegistrationSteps() {
        //попадаем на страницу для входа в аккаунт
        AuthorizationPage authPage = new AuthorizationPage(driver);
        //ждем пока загрузится страница
        authPage.waitForLoadPage();
        //нажимаем на ссылку Зарегистрироваться
        authPage.clickRegistrationRef();
        //попадаем на страницу регистрации
        RegistrationPage reistrationPage = new RegistrationPage(driver);
        //ждем пока загрузится страница
        reistrationPage.waitForLoadPage();
        //заполяем поля формы
        reistrationPage.setName(name);
        reistrationPage.setEmail(email);
        reistrationPage.setPassword(password);
        //нажимаем кнопку Зарегистрироваться
        reistrationPage.clickRegistrationButton();
        //ждем пока загрузится страница
        authPage.waitForLoadPage();
        Assert.assertTrue("Перехода на форму для входа в аккаунт не происходит", authPage.checkIsVisibleLoginFrame());


    }
}
