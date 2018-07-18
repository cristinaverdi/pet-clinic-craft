package es.eriktorr.example.petclinic.test.web;

import es.eriktorr.example.petclinic.Identifiable;
import es.eriktorr.example.petclinic.vets.model.Vet;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class RestAssuredSteps {

    public static RequestSpecification givenJsonApi(int port) {
        return RestAssured.given().port(port).accept(JSON).contentType(JSON);
    }

    public static void assertThatJsonPathContains(JsonPath jsonPath, Vet vet) {
        final Map<String, Object> vetsMap = objectMapOrEmpty(jsonPath, vet);
        assertThat(vetsMap.get("firstName")).isEqualTo(vet.getFirstName());
        assertThat(vetsMap.get("lastName")).isEqualTo(vet.getLastName());
    }

    private static <T extends Identifiable> Map<String, Object> objectMapOrEmpty(JsonPath jsonPath, T identifiableEntity) {
        final Map<String, Object> objectMap = jsonPath.get("find { it.id == " + identifiableEntity.getId() + " }");
        return objectMap != null ? objectMap : Collections.emptyMap();
    }

}
