package dao.implH2;

import config.JdbcConfiguration;
import dao.PersonDAO;
import dao.dto.PersonDTO;
import entities.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class PersonDAOImpl<M extends PersonDTO> implements PersonDAO<M> {

    protected final Connection conn;

    public PersonDAOImpl() {
        this.conn = JdbcConfiguration.getDBConnection();
    }

    @Override
    public void insert(PersonDTO personDTO) {

        Person newPerson = this.mapToEntity(personDTO);
        PreparedStatement statement = null;

        try {

            statement = this.insertStatement(newPerson);

            if (statement.executeUpdate() == 0) {
                throw new SQLException("No se pudo agregar a la Persona.");
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
    public void update(PersonDTO personDTO) {

        Person updatePerson = this.mapToEntity(personDTO);
        PreparedStatement statement = null;

        try {

            statement = this.updateStatement(updatePerson);

            Integer rs = statement.executeUpdate();

            if (rs == 0) {
                throw new SQLException("No se pudo actualizar la información.");
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
    public void delete(Integer id) {

        final String DELETE = "DELETE FROM fcsdb." + this.instanceOf() + " WHERE id = ?;";

        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(DELETE);
            statement.setInt(1, id);
            Integer rs = statement.executeUpdate();

            if (rs == 0) {
                throw new SQLException("No se pudo eliminar a la Persona.");
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
    public List<M> getAll() {

        final String GET_ALL = "SELECT * FROM fcsdb." + this.instanceOf();

        List<M> personList = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(GET_ALL);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                PersonDTO personDTO = this.mapToDTO(rs);

                personList.add((M) personDTO);

            }

            return personList;

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
    public M getByID(Integer id) {

        final String GET_BY_ID = "SELECT * FROM fcsdb." + this.instanceOf()
                + " WHERE id = ?;";

        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                throw new SQLException("No se encontró el miembro que busca en DB.");
            }

            PersonDTO personDTO = this.mapToDTO(rs);

            return (M) personDTO;

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

}
