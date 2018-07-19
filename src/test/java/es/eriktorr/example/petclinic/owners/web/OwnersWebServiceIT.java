package es.eriktorr.example.petclinic.owners.web;

import es.eriktorr.example.petclinic.test.InfrastructureInitializer;
import lombok.val;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import static es.eriktorr.example.petclinic.owners.datasets.Owners.OWNER_10;
import static es.eriktorr.example.petclinic.test.RestAssuredSteps.*;

@TestPropertySource(properties = {
        "spring.flyway.locations=classpath:/db/migration,classpath:/owners/db/migration,classpath:/pets/db/migration"
})
public class OwnersWebServiceIT extends InfrastructureInitializer {

    @Test public void
    view_information_pertaining_to_a_pet_owner() {
        val response = whenGetJsonRequest(port, "/owners/10");
        thenResponseStatusIs(response, 200);
        thenResponseBodyIsEqualTo(response, OWNER_10);
    }

    @Test public void
    fail_with_not_found_error_when_no_owner_exists_for_a_given_id() {
        val response = whenGetJsonRequest(port, "/owners/99");
        thenResponseStatusIs(response, 404);
    }

}
