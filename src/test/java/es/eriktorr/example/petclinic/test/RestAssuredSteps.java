package es.eriktorr.example.petclinic.test;

import es.eriktorr.example.petclinic.core.Identifiable;
import es.eriktorr.example.petclinic.owners.model.Owner;
import es.eriktorr.example.petclinic.vets.model.Vet;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.val;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class RestAssuredSteps {

    private static RequestSpecification givenJsonRestApi(int port) {
        return RestAssured.given().port(port).accept(JSON).contentType(JSON);
    }

    public static ValidatableResponse whenGetJsonRequest(int port, String path) {
        return givenJsonRestApi(port)
                .when().get(path)
                .then().log().ifError();
    }

    public static void thenResponseStatusIs(ValidatableResponse validatableResponse, int statusCode) {
        validatableResponse.statusCode(statusCode);
    }

    public static void thenResponseBodyContains(ValidatableResponse validatableResponse, List<Vet> expectedVets) {
        val jsonResponse = validatableResponse.extract().asString();
        val jsonPath = new JsonPath(jsonResponse);
        assertThatContains(jsonPath, expectedVets, Vet.class);
    }

    private static <T extends Identifiable> void assertThatContains(JsonPath jsonPath, List<T> expectedItems, Class<T> identifiableType) {
        final List<T> actualItems = jsonPath.getList("", identifiableType);
        assertThat(actualItems).as(String.format("Should contain %s items in any order", identifiableType.getSimpleName()))
                .containsExactlyInAnyOrderElementsOf(expectedItems);
    }

    public static void thenResponseBodyIsEqualTo(ValidatableResponse validatableResponse, Owner expectedOwner) {
        thenResponseBodyIsEqualTo(validatableResponse, expectedOwner, Owner.class);
    }

    private static <T extends Identifiable> void thenResponseBodyIsEqualTo(ValidatableResponse validatableResponse, T expectedItem, Class<T> identifiableType) {
        val jsonResponse = validatableResponse.extract().asString();
        val jsonPath = new JsonPath(jsonResponse);
        val actualItem = jsonPath.getObject("", identifiableType);
        assertThat(actualItem).isEqualTo(expectedItem);
    }

}
