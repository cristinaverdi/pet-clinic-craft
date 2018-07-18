package es.eriktorr.example.petclinic.vets.web;

import es.eriktorr.example.petclinic.test.ConcurrentIntegrationBase;
import es.eriktorr.example.petclinic.vets.model.Specialty;
import es.eriktorr.example.petclinic.vets.model.Vet;
import io.restassured.path.json.JsonPath;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;

import static es.eriktorr.example.petclinic.test.web.RestAssuredSteps.assertThatJsonPathContains;
import static es.eriktorr.example.petclinic.test.web.RestAssuredSteps.givenJsonApi;
import static java.util.Collections.singletonList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "petclinic.datasource.url=jdbc:postgresql://localhost/petclinic?currentSchema\\=test_${random.value}",
        "spring.flyway.locations=classpath:/db/migration,classpath:/vets/db/migration"
})
public class VetsWebServiceIT extends ConcurrentIntegrationBase {

    private static final Vet VET1 = new Vet(1, "James", "Carter", new HashSet<>(singletonList(
            new Specialty(-1, "none")
    )));
    private static final Vet VET2 = new Vet(2, "Helen", "Leary", new HashSet<>(singletonList(
            new Specialty(1, "radiology")
    )));
    private static final Vet VET3 = new Vet(3, "Linda", "Douglas", new HashSet<>(Arrays.asList(
            new Specialty(3, "dentistry"),
            new Specialty(2, "surgery")
    )));
    private static final Vet VET4 = new Vet(4, "Rafael", "Ortega", new HashSet<>(singletonList(
            new Specialty(2, "surgery")
    )));
    private static final Vet VET5 = new Vet(5, "Henry", "Stevens", new HashSet<>(singletonList(
            new Specialty(1, "radiology")
    )));
    private static final Vet VET6 = new Vet(6, "Sharon", "Jenkins", new HashSet<>(singletonList(
            new Specialty(-1, "none")
    )));

    @LocalServerPort
    private int port;

    @Test
    public void
    list_all_veterinarians_and_their_specialties() {
        val jsonResponse = givenJsonApi(this.port)
                .when().get("/vets")
                .then().log().ifError()
                .statusCode(HttpStatus.OK.value())
                .extract().asString();

        val jsonPath = new JsonPath(jsonResponse);

        assertThatJsonPathContains(jsonPath, VET1);
        assertThatJsonPathContains(jsonPath, VET2);
        assertThatJsonPathContains(jsonPath, VET3);
        assertThatJsonPathContains(jsonPath, VET4);
        assertThatJsonPathContains(jsonPath, VET5);
        assertThatJsonPathContains(jsonPath, VET6);
    }

}
