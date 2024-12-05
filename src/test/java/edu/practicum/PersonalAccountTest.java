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
public class PersonalAccountTest extends PrepareUtilsAndSteps {
    UserAfterCreate userAfterCreate;
    User user;

    public PersonalAccountTest(User user) {
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
    @Description("Checking the possibility of login in personal account")
    public void comeInPersonalAccount() {
        //для того чтобы перейти в личный кабинет пользователь должен залогиниться
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //проверяем возможность перехода в личный кабинет
        checkPossibilityComeInPersonalAccount(user);
    }

    @Test
    @Description("Checking the possibility to switch to the constructor from personal account")
    public void goToConstructorFromPersonalAccount() {
        //пользователь должен залогиниться
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //затем перейти в личный кабинет
        checkPossibilityComeInPersonalAccount(user);
        personalAccountPage.clickConstructorButton();
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Перехода в конструктор не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @Test
    @Description("Checking the possibility to switch to the constructor from personal account by click on logo")
    public void goToConstructorFromPersonalAccountByLogo() {
        //пользователь должен залогиниться
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //затем перейти в личный кабинет
        checkPossibilityComeInPersonalAccount(user);
        personalAccountPage.clickLogo();
        afterLoginPage.waitForLoadPage();
        Assert.assertTrue("Перехода в конструктор не происходит", afterLoginPage.checkIsVisibleMakeOrderButton());
    }

    @Test
    @Description("Checking the possibility of logout from personal account")
    public void logoutFromPersonalAccount() {
        //пользователь должен залогиниться
        userAfterCreate = checksCorrectCreateAndLoginUser(user);
        //затем перейти в личный кабинет
        checkPossibilityComeInPersonalAccount(user);
        personalAccountPage.clickLogoutButton();
        authPage.waitForLoadPage();
        Assert.assertTrue("Перехода на форму авторизации после выхода из личного кабинета не происходит", authPage.checkIsVisibleLoginFrame());
    }

    @After
    public void tearDown() {
        // удаляем созданного пользователя
        UserAPIClient.addBearerTokenInHeader(userAfterCreate.getAccessToken());
        UserAPIClient.delete();
    }
}
