package es.eriktorr.example.petclinic.vets.database;

import es.eriktorr.example.petclinic.Identifiable;
import lombok.Value;

@Value
public class VetEntity implements Identifiable {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final int specialtyId;
    private final String specialtyName;

}
