package es.eriktorr.example.petclinic.vets.web;

import es.eriktorr.example.petclinic.test.ConcurrentIntegrationBase;
import es.eriktorr.example.petclinic.vets.model.Vet;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static es.eriktorr.example.petclinic.test.RestAssuredSteps.*;
import static es.eriktorr.example.petclinic.vets.datasets.Vets.*;
import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "petclinic.datasource.url=jdbc:postgresql://localhost/petclinic?currentSchema\\=test_${random.value}",
        "spring.flyway.locations=classpath:/db/migration,classpath:/vets/db/migration"
})
public class VetsWebServiceIT extends ConcurrentIntegrationBase {

    private static final List<Vet> VETS = asList(VET1, VET2, VET3, VET4, VET5, VET6);

    @LocalServerPort
    private int port;

    @Test
    public void
    list_all_veterinarians_and_their_specialties() {
        val response = whenGetJsonRequest(port, "/vets");
        thenResponseStatusIs(response, 200);
        thenResponseBodyContains(response, VETS);
    }

}
