//класс для создания пользователя, для тестирования авторизации
package stellarsburgers.User;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static stellarsburgers.constant.SitePath.BASE_URI;

public class UserClient {
    private static final String CREATE_USER = "api/auth/register";
    private static final String INFO_USER = "api/auth/user";

    public UserClient() {
        RestAssured.baseURI = BASE_URI;
    }

    @Step("Создание пользователя")
    public Response create(UserModel user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(CREATE_USER);
    }

    @Step("Удаление пользователя")
    public Response delete(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(INFO_USER);
    }
}
