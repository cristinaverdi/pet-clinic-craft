package es.eriktorr.example.petclinic.vets.database;

import es.eriktorr.example.petclinic.vets.model.Specialty;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.util.Optional.ofNullable;

public class VetEntityRowMapper implements RowMapper<VetEntity> {

    @Override
    public VetEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new VetEntity(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                specialtyIdOrNone(resultSet),
                specialtyNameOrNone(resultSet)
        );
    }

    private int specialtyIdOrNone(ResultSet resultSet) throws SQLException {
        return ofNullable(resultSet.getObject("specialty_id", Integer.class)).orElse(Specialty.NONE.getId());
    }

    private String specialtyNameOrNone(ResultSet resultSet) throws SQLException {
        return ofNullable(resultSet.getString("specialty_name")).orElse(Specialty.NONE.getName());
    }

}
