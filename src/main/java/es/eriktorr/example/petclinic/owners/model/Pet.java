package es.eriktorr.example.petclinic.owners.model;

import es.eriktorr.example.petclinic.core.Identifiable;
import lombok.Value;

import java.time.LocalDate;

@Value
public class Pet implements Identifiable {

    private final int id;
    private final String name;
    private final LocalDate birthDate;
    private final int typeId;
    private final int ownerId;

}
