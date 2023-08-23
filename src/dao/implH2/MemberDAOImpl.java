package dao.implH2;

import config.JdbcConfiguration;
import dao.dto.MemberDTO;
import entities.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements dao.MemberDAO {

    static final String INSERT = "INSERT INTO fcsdb.member (name, surname, category, address, phone, email) "
            + "VALUES (?, ?, ?, ?, ?, ?);";

    static final String UPLOAD = "UPDATE fcsdb.member " +
            "SET name = ?, "
            + "surname = ?, "
            + "category = ?, "
            + "address = ?, "
            + "phone = ?, "
            + "email = ? "
            + "WHERE id = ?;";

    static final String DELETE = "DELETE FROM fcsdb.member WHERE id = ?;";

    static final String GET_ALL = "SELECT * FROM fcsdb.member;";

    static final String GET_BY_ID = "SELECT * FROM fcsdb.member WHERE id = ?;";

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
                throw new SQLException("No se pudo agregar al nuevo miembro.");
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
    public void update(MemberDTO memberDTO) {

        Member newMember = new Member(
                memberDTO.getId(),
                memberDTO.getName(),
                memberDTO.getSurname(),
                memberDTO.getCategory(),
                memberDTO.getAddress(),
                memberDTO.getPhone(),
                memberDTO.getEmail()
        );

        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(UPLOAD);
            statement.setString(1, newMember.getName());
            statement.setString(2, newMember.getSurname());
            statement.setString(3, newMember.getCategory());
            statement.setString(4, newMember.getAddress());
            statement.setString(5, newMember.getPhone());
            statement.setString(6, newMember.getEmail());
            statement.setInt(7, newMember.getId());

            Integer rs = statement.executeUpdate();

            if (rs == 0) {
                throw new SQLException("No se pudo actualizar la información del Miembro.");
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
    public void delete(Integer memberID) {

        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(DELETE);
            statement.setInt(1, memberID);
            Integer rs = statement.executeUpdate();

            if (rs == 0) {
                throw new SQLException("No se pudo eliminar al nuevo miembro.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public List<MemberDTO> getAll() {

        List<MemberDTO> memberList = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(GET_ALL);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String category = rs.getString("category");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                MemberDTO member = new MemberDTO(id, name, surname, category, address, phone, email);

                memberList.add(member);

            }

            return memberList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public MemberDTO getByID(Integer id) {

        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                throw new SQLException("No se encontró el miembro que busca en DB.");
            }

            Integer id_member = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String category = rs.getString("category");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String email = rs.getString("email");

            MemberDTO member = new MemberDTO(id_member, name, surname, category, address, phone, email);

            return member;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
