package dao.implH2;

import config.JdbcConfiguration;
import dao.dto.MemberDTO;
import entities.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MemberDAOImpl implements dao.MemberDAO {

    static final String INSERT = "INSERT INTO fcsdb.member (name, surname, category, address, phone, email) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private final Connection conn;

    public MemberDAOImpl() {
        this.conn = JdbcConfiguration.getDBConnection();
    }

    @Override
    public void insert(MemberDTO memberDTO) {

        Member newMember = new Member(
                memberDTO.getName(),
                memberDTO.getSurname(),
                memberDTO.getCategory(),
                memberDTO.getAddress(),
                memberDTO.getPhone(),
                memberDTO.getEmail()
        );

        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(INSERT);
            statement.setString(1, newMember.getName());
            statement.setString(2, newMember.getSurname());
            statement.setString(3, newMember.getCategory());
            statement.setString(4, newMember.getAddress());
            statement.setString(5, newMember.getPhone());
            statement.setString(6, newMember.getEmail());

            Integer rs = statement.executeUpdate();

            if (rs == 0) {
                throw new SQLException("No se pudo guardar la persona");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void update(MemberDTO member) {

    }

    @Override
    public void delete(MemberDTO member) {

    }

    @Override
    public List<MemberDTO> getAll() {
        return null;
    }

    @Override
    public MemberDTO getByID(Integer id) {
        return null;
    }
}
