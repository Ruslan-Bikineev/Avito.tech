package tests;

import data.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import models.Declaration;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic(value = "Объявление")
@Feature(value = "Проверка получения объявления по его идентификатору GET запросом через API")
public class GetMethodForDeclarationTests extends BaseTest {
    @Test
    @Story(value = "Получение данных несуществующего объявления (Вызов метода GET)")
    @Owner(value = "Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testGetMethodForNonExistingDeclaration() {
        RestAssured.given()
                .when()
                .get(TestData.GET_DECLARATION.replace(":id", "1000"))
                .then()
                .assertThat().statusCode(404)
                .body(matchesJsonSchemaInClasspath("schemas/GetDeclarationErrorResponse.json"));
    }

    @Test
    @Story(value = "Получение данных заранее подготовленного объявления (Вызов метода GET)")
    @Owner(value = "Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testGetSpecificDeclaration() {
        //PreConditions
        String identifier = RestAssured.given()
                .body(Declaration.getDefaultJsonBodyDeclaration())
                .when()
                .post(TestData.POST_DECLARATION)
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CreateDeclarationSuccessResponse.json"))
                .extract().jsonPath().getString("status").split(" ")[3];

        Declaration declaration = RestAssured.given()
                .when()
                .get(TestData.GET_DECLARATION.replace(":id", identifier))
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/GetDeclarationByIdentifierSuccessResponse.json"))
                .extract().body().jsonPath().getList("", Declaration.class).get(0);
        Assert.assertEquals(declaration.getId(), identifier, "Identifier is not the same");
        Assert.assertTrue(declaration.isEqualWithDefaultJsonBodyDeclaration(), "Declaration is not the same");
    }
}
