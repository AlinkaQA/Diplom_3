package ru.yandex.prakticum.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.yandex.prakticum.model.LoginRequest;
import ru.yandex.prakticum.model.UserData;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    @Step("Создание пользователя через API")
    public ValidatableResponse create(UserData user) {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then();
    }

    @Step("Авторизация пользователя через API")
    public ValidatableResponse login(UserData user) {
        LoginRequest req = new LoginRequest(user.getEmail(), user.getPassword());
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(req)
                .when()
                .post("/api/auth/login")
                .then();
    }

    @Step("Удаление пользователя через API")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .baseUri(BASE_URI)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then();
    }
}

