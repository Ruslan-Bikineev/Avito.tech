package helper;

import data.TestData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import models.Declaration;

import java.util.LinkedList;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ConditionPreparation {

    /**
     * Creates one declaration
     */
    @Step("Precondition: Create default declaration")
    public static String preConditionCreateDefaultDeclaration() {
        return RestAssured.given()
                .body(Declaration.getDefaultJsonBodyDeclaration())
                .when()
                .post(TestData.POST_DECLARATION)
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CreateDeclarationSuccessResponse.json"))
                .extract().jsonPath().getString("status").split(" ")[3];
    }

    /**
     * Creates list of declarations
     */
    @Step("Precondition: Create list of declarations")
    public static LinkedList<String> preConditionCreateListOfDeclaration(LinkedList<Declaration> declarations) {
        declarations.forEach(d -> d.setId(RestAssured.given()
                .body(d.getJsonBodyDeclaration())
                .when()
                .post(TestData.POST_DECLARATION)
                .then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/CreateDeclarationSuccessResponse.json"))
                .extract().jsonPath().getString("status").split(" ")[3]));
        return new LinkedList<>(declarations.stream().map(Declaration::getId).toList());
    }
}
