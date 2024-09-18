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

import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic(value = "Объявление")
@Feature(value = "Проверка получение всех объявлений по идентификатору продавца GET запросом через API")
public class GetAllDeclarationsBySellerIdTests extends BaseTest {
    @Test
    @Story(value = "Получение всех объявлений по идентификатору несуществующего продавца (Вызов метода GET) (Вызов метода GET)")
    @Owner(value = "Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testGetAllDeclarationsByNonExistingSellerId() {
        RestAssured.given()
                .when()
                .get(TestData.GET_ALL_DECLARATIONS_BY_SELLER_ID.replace(":sellerID", "121"))
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/GetAllDeclarationsBySellerIdSuccessResponse.json"));
    }

    @Test
    @Story(value = "Получение всех заранее подготовленных объявления по идентификатору продавца (Вызов метода GET)")
    @Owner(value = "Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testGetAllDeclarationsBySellerId() {
//        TODO: PreConditions for create temp declarations
        List<Declaration> declarations = RestAssured.given()
                .when()
                .get(TestData.GET_ALL_DECLARATIONS_BY_SELLER_ID.replace(":sellerID", "123456"))
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/GetAllDeclarationsBySellerIdSuccessResponse.json"))
                .extract().body().jsonPath().getList("", Declaration.class);
    }
}
