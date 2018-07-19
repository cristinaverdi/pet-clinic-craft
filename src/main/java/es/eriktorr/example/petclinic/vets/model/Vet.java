package es.eriktorr.example.petclinic.vets.model;

import es.eriktorr.example.petclinic.core.Identifiable;
import lombok.Value;

import java.util.Set;

@Value
public class Vet implements Identifiable {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Set<Specialty> specialties;

}
