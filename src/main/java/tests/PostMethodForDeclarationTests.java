package tests;

import data.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import models.Declaration;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic(value = "Объявление")
@Feature(value = "Проверка создание объявления POST запросом через API")
public class PostMethodForDeclarationTests extends BaseTest {
    @Test
    @Story(value = "Добавление нового объявления с пустым телом запроса JSON (Вызов метода POST)")
    @Owner(value = "Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testPostMethodForDeclarationEmptyBody() {
        RestAssured.given()
                .when()
                .post(TestData.POST_DECLARATION)
                .then()
                .assertThat().statusCode(400)
                .body(matchesJsonSchemaInClasspath("schemas/CreateDeclarationErrorResponse.json"));
    }

    @Test
    @Story(value = "Добавление нового объявления (Вызов метода POST)")
    @Owner(value = "Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testPostMethodForDeclaration() {
        RestAssured.given()
                .body(Declaration.getDefaultJsonBodyDeclaration())
                .when()
                .post(TestData.POST_DECLARATION)
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CreateDeclarationSuccessResponse.json"));
    }
}
