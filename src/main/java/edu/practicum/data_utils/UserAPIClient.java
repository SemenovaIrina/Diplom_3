package edu.practicum.data_utils;

import edu.practicum.models.User;
import edu.practicum.models.UserAfterCreate;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class UserAPIClient {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String USER_CREATE_REQUEST = "/api/auth/register";
    public static final String USER_DELETE_REQUEST = "/api/auth/user";
    public static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build();
    private static RequestSpecification request = given(REQUEST_SPEC);
    private static RequestSpecification requestWithAuth = given(REQUEST_SPEC);

    public static void addBearerTokenInHeader(String bearerToken) {
        requestWithAuth = given(REQUEST_SPEC);
        requestWithAuth = requestWithAuth
                .headers(
                        "Authorization",
                        "Bearer " + bearerToken);
    }

    @Step("Create a user with POST request to /api/auth/register")
    public static Response create(User user) {
        return request
                .body(user)
                .post(USER_CREATE_REQUEST);
    }

    @Step("Delete a user with DELETE request to /api/auth/user")
    public static Response delete() {
        return requestWithAuth
                .delete(USER_DELETE_REQUEST);
    }

    @Step("Checking the possibility of creating a new user in the system")
    public static UserAfterCreate checksCorrectCreateAndLoginUser(User user) {
        //выполняем запрос на создание пользователя
        Response response = create(user);
        UserAfterCreate userAfterCreate = response.as(UserAfterCreate.class);
        //проверяем статус код ответа
        Assert.assertEquals("Получаемый статус код при создании пользователя не соответствует ожидаемому", 200, response.statusCode());
        //проверяем тело ответа
        response.then().assertThat().body(notNullValue());
        Assert.assertEquals("Структура ответа не соответствует ожидаемой", response.as(UserAfterCreate.class).getClass(), UserAfterCreate.class);
        userAfterCreate.setAccessToken(userAfterCreate.getAccessToken()); //убираем все лишнее из токена в ответе

        return userAfterCreate;
    }
}
