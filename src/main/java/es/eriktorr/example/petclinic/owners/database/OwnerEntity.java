package es.eriktorr.example.petclinic.owners.database;

import es.eriktorr.example.petclinic.core.Identifiable;
import es.eriktorr.example.petclinic.owners.model.Pet;
import lombok.Value;

@Value
public class OwnerEntity implements Identifiable {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String telephone;
    private final Pet pet;

}
