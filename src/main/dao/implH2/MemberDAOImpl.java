package main.dao.implH2;

import main.dao.MemberDAO;
import main.dao.dto.MemberDTO;
import main.dao.dto.PersonDTO;
import main.entities.Member;
import main.entities.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MemberDAOImpl extends PersonDAOImpl<MemberDTO> implements MemberDAO{

    @Override
    public PersonDTO mapToDTO(ResultSet rs) throws SQLException {

        //Mapear ResultSet a MemberDTO al pedir un Miembro a DB.
        //Devuelve el Miembro

        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String category = rs.getString("category");
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        String email = rs.getString("email");

        MemberDTO member = new MemberDTO(id, name, surname, category, address, phone, email);

        return member;
    }

    @Override
    public Person mapToEntity(PersonDTO personDTO) {

        //Mapear MemberDTO a entidad Member para guardar o actualizar Miembro en DB.

        return new Member(
                personDTO.getId(),
                personDTO.getName(),
                personDTO.getSurname(),
                personDTO.getCategory(),
                personDTO.getAddress(),
                personDTO.getPhone(),
                personDTO.getEmail()
        );
    }

    @Override
    public PreparedStatement insertStatement(Person person) throws SQLException {

        //Crea el statement para guardar un Miembro en DB
        //Devuelve statement

        PreparedStatement statement = null;

        String INSERT = "INSERT INTO fcsdb.member (name, surname, category, address, phone, email) "
                + "VALUES (?, ?, ?, ?, ?, ?);";

        statement = this.conn.prepareStatement(INSERT);

        statement.setString(1, person.getName());
        statement.setString(2, person.getSurname());
        statement.setString(3, person.getCategory());
        statement.setString(4, person.getAddress());
        statement.setString(5, person.getPhone());
        statement.setString(6, person.getEmail());

        return statement;

    }

    @Override
    public PreparedStatement updateStatement(Person updatedPerson) throws SQLException {

        //Crea el statement para actualizar los datos de un Miembro en DB
        //Devuelve statement con los datos para actualizar

        PreparedStatement statement = null;

        String UPLOAD = "UPDATE fcsdb.member"
                + " SET name = ?, "
                + "surname = ?, "
                + "category = ?, "
                + "address = ?, "
                + "phone = ?, "
                + "email = ? "
                + "WHERE id = ?;";

        statement = this.conn.prepareStatement(UPLOAD);
        statement.setString(1, updatedPerson.getName());
        statement.setString(2, updatedPerson.getSurname());
        statement.setString(3, updatedPerson.getCategory());
        statement.setString(4, updatedPerson.getAddress());
        statement.setString(5, updatedPerson.getPhone());
        statement.setString(6, updatedPerson.getEmail());
        statement.setInt(7, updatedPerson.getId());

        return statement;
    }

    @Override
    public String instanceOf() {
        return "member";
    }

}
