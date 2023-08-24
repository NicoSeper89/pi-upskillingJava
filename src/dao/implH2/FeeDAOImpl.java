package dao.implH2;

import config.JdbcConfiguration;
import dao.FeeDAO;
import dao.dto.FeeDTO;
import entities.Fee;
import entities.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FeeDAOImpl implements FeeDAO {

    static final String INSERT = "INSERT INTO fcsdb.fee (amount, local_date, owner_id)" +
            "VALUES(?, ?, ?);";

    static final String UPLOAD = "UPDATE fcsdb.fee " +
            "SET amount = ?, "
            + "paid = ? "
            + "WHERE id = ?;";

    static final String DELETE = "DELETE FROM fcsdb.fee WHERE id = ?;";

    private final Connection conn;

    public FeeDAOImpl() {
        this.conn = JdbcConfiguration.getDBConnection();
    }

    @Override
    public void insert(FeeDTO feeDTO) {

        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(INSERT);

            Fee newFee = new Fee(
                    feeDTO.getAmount(),
                    new Member(feeDTO.getOwner().getId())
            );

            LocalDateTime currentDateAndTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = currentDateAndTime.format(formatter);

            statement.setInt(1, newFee.getAmount());
            statement.setString(2, formattedDate);
            statement.setInt(3, newFee.getOwner().getId());

            Integer rs = statement.executeUpdate();

            if (rs == 0) {
                throw new SQLException("No se pudo generar la cuota.");
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
            statement = conn.prepareStatement(UPLOAD);

            Fee updatedFee = new Fee(feeDTO.getId(),
                    feeDTO.getAmount(),
                    feeDTO.getPaid()
            );

            statement.setInt(1, updatedFee.getAmount());
            statement.setBoolean(2, updatedFee.getPaid());
            statement.setInt(3, updatedFee.getId());

            Integer rs = statement.executeUpdate();

            if (rs == 0) {
                throw new SQLException("No se pudo actualizar la información de la cuota.");
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
            statement = conn.prepareStatement(DELETE);

            statement.setInt(1, id);

           Integer res = statement.executeUpdate();

           if (res == 0) {
               throw new SQLException("No se pudo eliminar la cuota");
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
        return null;
    }

    @Override
    public FeeDTO getByID(Integer id) {
        return null;
    }
}
