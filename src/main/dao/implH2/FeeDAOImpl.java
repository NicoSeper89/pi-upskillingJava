package main.dao.implH2;

import main.dao.FeeDAO;
import main.dao.dto.FeeDTO;
import main.dao.dto.MemberDTO;
import main.entities.Fee;
import main.entities.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FeeDAOImpl implements FeeDAO {

    static final String INSERT = "INSERT INTO fcsdb.fee (amount, generated_date, owner_member_id)" +
            "VALUES(?, ?, ?);";

    static final String UPLOAD = "UPDATE fcsdb.fee " +
            "SET amount = ?, "
            + "paid = ? "
            + "WHERE id = ?;";

    static final String DELETE = "DELETE FROM fcsdb.fee WHERE id = ?;";

    static final String GET_ALL = "SELECT * FROM fcsdb.fee;";

    static final String GET_BY_ID = "SELECT * FROM fcsdb.fee WHERE id = ?;";

    static final String GET_ALL_MEMBER_FEES = "SELECT * FROM fcsdb.fee WHERE owner_member_id = ?";

    static final String DELETE_ALL_MEMBER_FEES = "DELETE FROM fcsdb.fee WHERE owner_member_id = ?";

    private final Connection conn;

    public FeeDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(FeeDTO feeDTO) {

        PreparedStatement statement = null;
        try {

            //Mapear entidad de Cuota con datos de DTO Cuota.
            Fee newFee = new Fee(
                    feeDTO.getAmount(),
                    new Member(feeDTO.getOwner().getId())
            );

            //Obtener fecha actual.
            LocalDateTime currentDateAndTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
            String formattedDate = currentDateAndTime.format(formatter);

            //Instancia PreparedStatement con sentencia SQL INSERT
            statement = conn.prepareStatement(INSERT);

            //Setear statement con importe, fecha actual e ID del Miembro al que pertenecerá la Cuota
            statement.setInt(1, newFee.getAmount());
            statement.setString(2, formattedDate);
            statement.setInt(3, newFee.getOwner().getId());

            //Ejecutar sentencia SQL para guardar Cuota en DB.
            Integer rs = statement.executeUpdate();

            //Comprobar si se creo Cuota en DB.
            if (rs == 0) {
                throw new SQLException("No se pudo generar la Cuota.");
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
    public void update(FeeDTO feeDTO) {

        PreparedStatement statement = null;

        try {

            //Mapear entidad de Cuota con datos de DTO Cuota.
            Fee updatedFee = new Fee(feeDTO.getId(),
                    feeDTO.getAmount(),
                    feeDTO.getPaid()
            );

            //Instancia PreparedStatement con sentencia SQL UPLOAD
            statement = conn.prepareStatement(UPLOAD);

            //Setear statement con datos actualizados de Cuota
            statement.setInt(1, updatedFee.getAmount());
            statement.setBoolean(2, updatedFee.getPaid());
            statement.setInt(3, updatedFee.getId());

            //Ejecutar sentencia SQL para actualizar datos de Cuota en DB.
            Integer rs = statement.executeUpdate();

            //Comprobar si se actualizó la Cuota en DB.
            if (rs == 0) {
                throw new SQLException("No se pudo actualizar la información de la Cuota.");
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

        PreparedStatement statement = null;

        try {
            //Instancia PreparedStatement con sentencia SQL DELETE
            statement = conn.prepareStatement(DELETE);

            //Setear statement con ID de Cuota a eliminar
            statement.setInt(1, id);

            //Ejecutar sentencia SQL para eliminar Cuota en DB.
            Integer res = statement.executeUpdate();

            //Comprobar si se eliminó la Cuota en DB.
            if (res == 0) {
                throw new SQLException("No se pudo eliminar la Cuota");
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
    public List<FeeDTO> getAll() {

        PreparedStatement statement = null;

        try {
            //Instancia PreparedStatement con sentencia SQL SELECT *
            statement = conn.prepareStatement(GET_ALL);

            //Ejecutar sentencia y obtener todas las Cuotas de DB.
            ResultSet rs = statement.executeQuery();

            //Instanciar listada de DTO Cuotas para guardar resultados obtenidos de DB.
            List<FeeDTO> feeList = new ArrayList<>();

            //Iterar en los resultados obtenidos e ir guardándolos en la lista de Cuotas.
            while (rs.next()) {

                Integer id = rs.getInt("id");
                Integer amount = rs.getInt("amount");
                String generationDate = rs.getString("generated_date");
                Boolean paid = rs.getBoolean("paid");
                Integer id_owner = rs.getInt("owner_member_id");

                FeeDTO fee = new FeeDTO(id, amount, generationDate, paid, new MemberDTO(id_owner));

                feeList.add(fee);
            }

            //Devolver lista de Cuotas existentes en DB.
            return feeList;

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
    public FeeDTO getByID(Integer id) {
        PreparedStatement statement = null;

        try {
            //Instancia PreparedStatement con sentencia SQL SELECT * / WHERE = id
            statement = conn.prepareStatement(GET_BY_ID);

            //Setear statement con ID de Cuota que se quiere obtener de DB.
            statement.setInt(1, id);

            //Ejecutar sentencia y obtener la Cuota de DB.
            ResultSet rs = statement.executeQuery();

            //Comprobar que la Cuota con el ID existía en DB.
            if (!rs.next()) {
                throw new SQLException("No se encontró la Cuota que busca en DB.");
            }

            //Crear DTO de Cuota con el resultado de la consulta a DB.
            Integer feeID = rs.getInt("id");
            Integer amount = rs.getInt("amount");
            String generationDate = rs.getString("generated_date");
            Boolean paid = rs.getBoolean("paid");
            Integer id_owner = rs.getInt("owner_member_id");

            FeeDTO fee = new FeeDTO(feeID, amount, generationDate, paid, new MemberDTO(id_owner));

            //Devolver Cuota.
            return fee;

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
    public List<FeeDTO> getAllMemberFees(Integer memberId) {


        PreparedStatement statement = null;

        List<FeeDTO> feesList = new ArrayList<>();

        try {

            statement = this.conn.prepareStatement(GET_ALL_MEMBER_FEES);
            statement.setInt(1, memberId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Integer id = resultSet.getInt("id");
                Integer amount = resultSet.getInt("amount");
                String generationDate = resultSet.getString("generated_date");
                Boolean paid = resultSet.getBoolean("paid");
                Integer id_owner = resultSet.getInt("owner_member_id");

                FeeDTO fee = new FeeDTO(id, amount, generationDate, paid, new MemberDTO(id_owner));

                feesList.add(fee);

            }

            return feesList;

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
    public void deleteAllMemberFees(Integer memberId) {


        PreparedStatement statement = null;

        try {
            //Instancia PreparedStatement con sentencia SQL DELETE
            statement = conn.prepareStatement(DELETE_ALL_MEMBER_FEES);

            //Setear statement con ID Miembro de Cuota a eliminar
            statement.setInt(1, memberId);

            //Ejecutar sentencia SQL para eliminar Cuota en DB.
            Integer res = statement.executeUpdate();

            //Comprobar si se eliminó la Cuota en DB.
            if (res == 0) {
                throw new SQLException("No se pudo eliminar las Cuotas del Miembro");
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
}
