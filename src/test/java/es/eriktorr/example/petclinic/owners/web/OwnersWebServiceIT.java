package es.eriktorr.example.petclinic.owners.web;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static es.eriktorr.example.petclinic.owners.datasets.Owners.OWNER_10;
import static es.eriktorr.example.petclinic.test.RestAssuredSteps.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "petclinic.datasource.url=jdbc:postgresql://localhost/petclinic?currentSchema\\=test_${random.value}",
        "spring.flyway.locations=classpath:/db/migration,classpath:/owners/db/migration,classpath:/pets/db/migration"
})
public class OwnersWebServiceIT {

    @LocalServerPort
    private int port;

    @Test
    public void
    view_information_pertaining_to_a_pet_owner() {
        val response = whenGetJsonRequest(port, "/owners/10");
        thenResponseStatusIs(response, 200);
        thenResponseBodyIsEqualTo(response, OWNER_10);
    }

    @Test
    public void
    fail_with_not_found_error_when_no_owner_exists_for_a_given_id() {
        val response = whenGetJsonRequest(port, "/owners/99");
        thenResponseStatusIs(response, 404);
    }

}
