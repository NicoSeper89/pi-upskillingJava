package main.dao.implH2;

import main.dao.dto.FeeDTO;
import main.dao.dto.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class FeeDAOImplTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @InjectMocks
    private FeeDAOImpl feeDao;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    void insert_ShouldInsertFee_WhenValidFeeDto() throws SQLException {

        // GIVEN
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-yyyy"));

        FeeDTO feeDto = new FeeDTO(
                1,
                4000,
                data,
                false,
                new MemberDTO(1)
        );

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // WHEN
        feeDao.insert(feeDto);

        // THEN
        verify(mockPreparedStatement).setInt(1, feeDto.getAmount());
        verify(mockPreparedStatement).setString(2, feeDto.getGeneratedDate());
        verify(mockPreparedStatement).setInt(3, feeDto.getOwner().getId());
        verify(mockPreparedStatement).executeUpdate();

    }

    @Test
    void update_ShouldUpdateFee_WhenValidFeeDto() throws SQLException {

        // GIVEN
        FeeDTO feeDto = new FeeDTO(1,4000, false);

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // WHEN
        feeDao.update(feeDto);

        // THEN
        verify(mockPreparedStatement).setInt(1, feeDto.getAmount());
        verify(mockPreparedStatement).setBoolean(2, feeDto.getPaid());
        verify(mockPreparedStatement).setInt(3, feeDto.getId());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void delete_ShouldDeleteFee_WhenValidId() throws SQLException {

        // GIVEN
        int feeId = 1;

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // WHEN
        feeDao.delete(feeId);

        // THEN
        verify(mockPreparedStatement).setInt(1, feeId);
        verify(mockPreparedStatement).executeUpdate();
    }
}