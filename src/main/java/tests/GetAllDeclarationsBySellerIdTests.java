package tests;

import data.TestData;
import helper.AssertHelper;
import helper.ConditionPreparation;
import helper.ModelsHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import models.Declaration;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static data.TestData.AMOUNT_CREATE_DECLARATIONS;
import static data.TestData.NON_EXISTING_SELLER_ID;
import static data.TestData.SELLER_ID_TO_CREATE_DECLARATIONS;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic(value = "Объявление")
@Feature(value = "Проверка получение всех объявлений по идентификатору продавца GET запросом через API")
public class GetAllDeclarationsBySellerIdTests extends BaseTest {
    @Test
    @Story(value = "Получение всех объявлений по идентификатору несуществующего продавца (Вызов метода GET)")
    @Owner(value = "Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testGetAllDeclarationsByNonExistingSellerId() {
        RestAssured.given()
                .when()
                .get(TestData.GET_ALL_DECLARATIONS_BY_SELLER_ID.replace(":sellerID", NON_EXISTING_SELLER_ID.toString()))
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/GetAllDeclarationsBySellerIdSuccessResponse.json"));
    }

    @Test
    @Story(value = "Получение всех заранее подготовленных объявления по идентификатору продавца (Вызов метода GET)")
    @Owner(value = "Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testGetAllDeclarationsBySellerId() {
        LinkedList<Declaration> expectedDeclarations = ModelsHelper.getListDeclaration(
                AMOUNT_CREATE_DECLARATIONS, SELLER_ID_TO_CREATE_DECLARATIONS);
        ConditionPreparation.preConditionCreateListOfDeclaration(expectedDeclarations);

        List<Declaration> declarations = RestAssured.given()
                .when()
                .get(TestData.GET_ALL_DECLARATIONS_BY_SELLER_ID.replace(":sellerID", SELLER_ID_TO_CREATE_DECLARATIONS.toString()))
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/GetAllDeclarationsBySellerIdSuccessResponse.json"))
                .extract().body().jsonPath().getList("", Declaration.class);
        AssertHelper.checkEqualsTwoLinkedLists(expectedDeclarations, new LinkedList(declarations));
    }
}
