package main.dao.implH2;

import main.config.JdbcConfiguration;
import main.dao.PersonDAO;
import main.dao.dto.PersonDTO;
import main.entities.Person;

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

        //Mapear DTO a Entidad
        Person newPerson = this.mapToEntity(personDTO);
        PreparedStatement statement = null;

        try {

            //Devuelve la declaración SQL para guardar en DB una entidad de Persona (Un Miembro por ahora).
            statement = this.insertStatement(newPerson);

            //Comprobar si la Persona existe en DB.
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

        //Mapear DTO a Entidad
        Person updatePerson = this.mapToEntity(personDTO);
        PreparedStatement statement = null;

        try {

            //Devuelve la declaración SQL para actualizar la información en DB una entidad de Persona
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

        //Statement para borrar una Persona en DB
        //this.instanceOf() devuelve el tipo de Persona que se borra.
        final String DELETE = "DELETE FROM fcsdb." + this.instanceOf() + " WHERE id = ?;";
        PreparedStatement statement = null;

        try {
            //Borrar Persona en DB por ID.
            statement = conn.prepareStatement(DELETE);
            statement.setInt(1, id);
            Integer rs = statement.executeUpdate();

            //Comprobar si se borro correctamente la Persona de DB.
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

        //Statement para obtener todas las Personas de DB.
        //this.instanceOf() devuelve el tipo de Personas que se quiere obtener de DB.
        final String GET_ALL = "SELECT * FROM fcsdb." + this.instanceOf();

        //Instanciar lista de tipo M que extiende de PersonDTO
        List<M> personList = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            //Obtener todas las Personas de tipo M de DB
            statement = conn.prepareStatement(GET_ALL);
            ResultSet rs = statement.executeQuery();

            //Guardar en la lista los resultados, si los hubiera.
            while (rs.next()) {

                //Mapear resultado a PersonDTO.
                PersonDTO personDTO = this.mapToDTO(rs);

                //Castear PersonDTO a M para guardar en la lista.
                personList.add((M) personDTO);

            }

            //Devolver lista de M que extienden de PersonDTO.
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

        //Statement para obtener Persona tipo M por su ID de DB.
        //this.instanceOf() devuelve el tipo de Personas que se quiere obtener de DB.
        final String GET_BY_ID = "SELECT * FROM fcsdb." + this.instanceOf()
                + " WHERE id = ?;";

        PreparedStatement statement = null;

        try {
            //Obtener Persona de tipo M de DB
            statement = conn.prepareStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            //Comprobar si se obtuvo la Persona.
            if (!rs.next()) {
                throw new SQLException("No se encontró el miembro que busca en DB.");
            }

            //Mapear resultado a PersonaDTO
            PersonDTO personDTO = this.mapToDTO(rs);

            //Castear PersonaDTO a tipo M y devolverlo.
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
