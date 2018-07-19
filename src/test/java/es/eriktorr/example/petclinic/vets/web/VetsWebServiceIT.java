package es.eriktorr.example.petclinic.vets.web;

import es.eriktorr.example.petclinic.test.InfrastructureInitializer;
import es.eriktorr.example.petclinic.vets.model.Vet;
import lombok.val;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static es.eriktorr.example.petclinic.test.RestAssuredSteps.*;
import static es.eriktorr.example.petclinic.vets.datasets.Vets.*;
import static java.util.Arrays.asList;

@TestPropertySource(properties = {
        "spring.flyway.locations=classpath:/db/migration,classpath:/vets/db/migration"
})
public class VetsWebServiceIT extends InfrastructureInitializer {

    private static final List<Vet> VETS = asList(VET_1, VET_2, VET_3, VET_4, VET_5, VET_6);

    @Test
    public void
    list_all_veterinarians_and_their_specialties() {
        val response = whenGetJsonRequest(port, "/vets");
        thenResponseStatusIs(response, 200);
        thenResponseBodyContains(response, VETS);
    }

}
