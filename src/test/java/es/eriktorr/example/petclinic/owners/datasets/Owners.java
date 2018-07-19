package es.eriktorr.example.petclinic.owners.datasets;

import es.eriktorr.example.petclinic.owners.model.Owner;
import es.eriktorr.example.petclinic.owners.model.Pet;

import java.time.LocalDate;
import java.util.HashSet;

import static java.util.Arrays.asList;

public interface Owners {

    Owner OWNER_10 = new Owner(10, "Carlos", "Estaban",
            "2335 Independence La.", "Waunakee", "6085555487", new HashSet<>(asList(
            new Pet(12, "Lucky", LocalDate.of(2000, 6, 24), 2, 10),
            new Pet(13, "Sly", LocalDate.of(2002, 6, 8), 1, 10)
    )));

}
