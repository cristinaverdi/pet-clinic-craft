package es.eriktorr.example.petclinic.vets.datasets;

import es.eriktorr.example.petclinic.vets.model.Specialty;
import es.eriktorr.example.petclinic.vets.model.Vet;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public interface Vets {

    Vet VET1 = new Vet(1, "James", "Carter", new HashSet<>(singletonList(
            new Specialty(-1, "none")
    )));

    Vet VET2 = new Vet(2, "Helen", "Leary", new HashSet<>(singletonList(
            new Specialty(1, "radiology")
    )));

    Vet VET3 = new Vet(3, "Linda", "Douglas", new HashSet<>(asList(
            new Specialty(3, "dentistry"),
            new Specialty(2, "surgery")
    )));

    Vet VET4 = new Vet(4, "Rafael", "Ortega", new HashSet<>(singletonList(
            new Specialty(2, "surgery")
    )));

    Vet VET5 = new Vet(5, "Henry", "Stevens", new HashSet<>(singletonList(
            new Specialty(1, "radiology")
    )));

    Vet VET6 = new Vet(6, "Sharon", "Jenkins", new HashSet<>(singletonList(
            new Specialty(-1, "none")
    )));

}
