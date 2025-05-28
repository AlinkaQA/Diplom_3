package ru.yandex.prakticum.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.yandex.prakticum.constant.Endpoints;
import ru.yandex.prakticum.model.LoginRequest;
import ru.yandex.prakticum.model.UserData;

import static io.restassured.RestAssured.given;

/**
 * Клиент для работы с API пользователей Stellar Burgers
 */
public class UserClient {
    @Step("Создание пользователя через API")
    public ValidatableResponse create(UserData user) {
        return given()
                .baseUri(Endpoints.BASE_URI)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(Endpoints.REGISTER)
                .then();
    }

    @Step("Авторизация пользователя через API")
    public ValidatableResponse login(UserData user) {
        LoginRequest req = new LoginRequest(user.getEmail(), user.getPassword());
        return given()
                .baseUri(Endpoints.BASE_URI)
                .contentType(ContentType.JSON)
                .body(req)
                .when()
                .post(Endpoints.LOGIN)
                .then();
    }

    @Step("Удаление пользователя через API")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .baseUri(Endpoints.BASE_URI)
                .header("Authorization", accessToken)
                .when()
                .delete(Endpoints.DELETE_USER)
                .then();
    }
}
