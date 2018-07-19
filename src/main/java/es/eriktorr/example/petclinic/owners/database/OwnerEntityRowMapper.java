package es.eriktorr.example.petclinic.owners.database;

import es.eriktorr.example.petclinic.owners.model.Pet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerEntityRowMapper implements RowMapper<OwnerEntity> {

    @Override
    public OwnerEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new OwnerEntity(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("address"),
                resultSet.getString("city"),
                resultSet.getString("telephone"),
                petFrom(resultSet)
        );
    }

    private Pet petFrom(ResultSet resultSet) throws SQLException {
        return new Pet(
                resultSet.getInt("pet_id"),
                resultSet.getString("name"),
                resultSet.getDate("birth_date").toLocalDate(),
                resultSet.getInt("type_id"),
                resultSet.getInt("id")
        );
    }

}
