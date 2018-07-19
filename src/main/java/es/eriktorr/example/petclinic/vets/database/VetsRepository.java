package es.eriktorr.example.petclinic.vets.database;

import es.eriktorr.example.petclinic.vets.model.Specialty;
import es.eriktorr.example.petclinic.vets.model.Vet;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class VetsRepository {

    private static final String LIST_ALL_VETS_SQL = "with t1 as (" +
            "select vets.id, vets.first_name, vets.last_name, vet_specialties.specialty_id from vets left join vet_specialties on vets.id = vet_specialties.vet_id" +
            "), t2 as (" +
            "select vet_specialties.specialty_id, specialties.name from vet_specialties, specialties where specialties.id = vet_specialties.specialty_id" +
            ")" +
            "select distinct t1.id, t1.first_name, t1.last_name, t2.specialty_id, t2.name as specialty_name from t1 left join t2 on t1.specialty_id = t2.specialty_id order by t1.id";

    private final JdbcTemplate jdbcTemplate;

    public VetsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Vet> listAll() {
        return entitiesGroupedById().entrySet().stream().map(entityGroup -> {
            val entities = entityGroup.getValue();
            val specialties = specialtiesFrom(entities);
            val firstEntity = entities.get(0);
            return vetFrom(firstEntity, specialties);
        }).collect(Collectors.toList());
    }

    private Map<Integer, List<VetEntity>> entitiesGroupedById() {
        return jdbcTemplate.query(LIST_ALL_VETS_SQL, new VetEntityRowMapper()).stream()
                .collect(groupingBy(VetEntity::getId));
    }

    private Set<Specialty> specialtiesFrom(List<VetEntity> entities) {
        return entities.stream()
                .map(entity -> new Specialty(entity.getSpecialtyId(), entity.getSpecialtyName()))
                .collect(Collectors.toSet());
    }

    private Vet vetFrom(VetEntity entity, Set<Specialty> specialties) {
        return new Vet(entity.getId(), entity.getFirstName(), entity.getLastName(), specialties);
    }

}
