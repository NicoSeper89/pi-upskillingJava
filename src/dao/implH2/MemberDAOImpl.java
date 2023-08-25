package dao.implH2;

import dao.MemberDAO;
import dao.dto.MemberDTO;
import dao.dto.PersonDTO;
import entities.Member;
import entities.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MemberDAOImpl extends PersonDAOImpl<MemberDTO> implements MemberDAO{

    @Override
    public PersonDTO mapToDTO(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String category = rs.getString("category");
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        String email = rs.getString("email");

        PersonDTO person = new MemberDTO(id, name, surname, category, address, phone, email);

        return person;
    }

    @Override
    public Person mapToEntity(PersonDTO personDTO) {
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
