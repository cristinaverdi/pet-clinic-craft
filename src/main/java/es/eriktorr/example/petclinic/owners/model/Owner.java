package es.eriktorr.example.petclinic.owners.model;

import es.eriktorr.example.petclinic.core.Identifiable;
import lombok.Value;

import java.util.Set;

@Value
public class Owner implements Identifiable {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String telephone;
    private final Set<Pet> pets;

}
