package main.dao;

import main.dao.dto.PersonDTO;
import main.entities.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PersonDAO<T extends PersonDTO> extends DAO<T> {

    //Mapear resultSet de DB a DTO.
    PersonDTO mapToDTO(ResultSet rs) throws SQLException;

    //Mapear DTO a entidad para operaciones en DB.
    Person mapToEntity(PersonDTO personDTO);

    //Generar sentencia SQL INSERT para almacenar entidad en DB.
    PreparedStatement insertStatement(Person person) throws SQLException;

    //Generar sentencia SQL UPDATE para actualizar entidad en DB.
    PreparedStatement updateStatement(Person person) throws SQLException;

    String instanceOf();

}


