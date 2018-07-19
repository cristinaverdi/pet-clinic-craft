package es.eriktorr.example.petclinic.owners.web;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "petclinic.datasource.url=jdbc:postgresql://localhost/petclinic?currentSchema\\=test_${random.value}",
        "spring.flyway.locations=classpath:/db/migration,classpath:/owners/db/migration,classpath:/pets/db/migration"
})
public class OwnersWebServiceIT {

    // View information pertaining to a pet owner

}
