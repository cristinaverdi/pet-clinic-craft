package es.eriktorr.example.petclinic.owners.database;

import es.eriktorr.example.petclinic.owners.model.Owner;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class OwnersRepository {

    private static final String FIND_OWNER_BY_ID_SQL = "select owners.id, first_name, last_name, address, city, telephone, " +
            "pets.id as pet_id, name, birth_date, type_id " +
            "from owners, pets " +
            "where owners.id = ? and owners.id = pets.owner_id " +
            "order by pet_id asc";

    private final JdbcTemplate jdbcTemplate;

    public OwnersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Owner> findOwnerBy(int ownerId) {
        val entities = jdbcTemplate.query(FIND_OWNER_BY_ID_SQL, new Object[]{ownerId}, new OwnerEntityRowMapper());
        return !entities.isEmpty() ? Optional.of(ownerFromEntities(entities)) : Optional.empty();
    }

    private Owner ownerFromEntities(List<OwnerEntity> entities) {
        val baseEntity = entities.get(0);
        val baseOwner = new Owner(
                baseEntity.getId(),
                baseEntity.getFirstName(),
                baseEntity.getLastName(),
                baseEntity.getAddress(),
                baseEntity.getCity(),
                baseEntity.getTelephone(),
                new HashSet<>()
        );
        return entities.stream().reduce(baseOwner, (owner, entity) -> {
            owner.getPets().add(entity.getPet());
            return owner;
        }, (owner, owner2) -> owner);
    }

}
