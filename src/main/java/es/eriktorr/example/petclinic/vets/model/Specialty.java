package es.eriktorr.example.petclinic.vets.model;

import es.eriktorr.example.petclinic.Identifiable;
import lombok.Value;

@Value
public class Specialty implements Identifiable {

    private final int id;
    private final  String name;

    public static final Specialty NONE = new Specialty(-1, "none");

}
