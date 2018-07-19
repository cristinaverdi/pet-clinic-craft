package es.eriktorr.example.petclinic.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "petclinic.datasource.jdbc-url=jdbc:postgresql://localhost/petclinic?currentSchema\\=test_${random.value}"
})
public abstract class InfrastructureInitializer extends ConcurrentIntegrationBase {

    @LocalServerPort
    protected int port;

}
