package dao;

import dao.dto.PersonDTO;
import entities.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PersonDAO<T extends PersonDTO> extends DAO<T> {

    PersonDTO mapToDTO(ResultSet rs) throws SQLException;

    Person mapToEntity(PersonDTO personDTO);

    PreparedStatement insertStatement(Person person) throws SQLException;

    PreparedStatement updateStatement(Person person) throws SQLException;

    String instanceOf();

}


