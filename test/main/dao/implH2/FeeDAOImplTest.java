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
import java.sql.ResultSet;
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
    @Mock
    private ResultSet mockResultSet;
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

    @Test
    void getAll_ShouldReturnListOfFeesDtos_WhenDatabaseHasData() throws SQLException {
        // GIVEN
        List<FeeDTO> feeList = new ArrayList<>();

        feeList.add(new FeeDTO(1,
                4000,
                "09-2023",
                false,
                new MemberDTO(1)));

        feeList.add(new FeeDTO(2,
                2000,
                "09-2023",
                true,
                new MemberDTO(2)));

        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getInt("amount")).thenReturn(4000, 2000);
        when(mockResultSet.getString("generated_date")).thenReturn("09-2023", "09-2023");
        when(mockResultSet.getBoolean("paid")).thenReturn(false, true);
        when(mockResultSet.getInt("owner_member_id")).thenReturn(1, 2);

        // WHEN
        List<FeeDTO> resultList = feeDao.getAll();

        // THEN
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getInt("amount");
        verify(mockResultSet, times(2)).getString("generated_date");
        verify(mockResultSet, times(2)).getBoolean("paid");
        verify(mockResultSet, times(2)).getInt("owner_member_id");

        // Assertions
        Assertions.assertEquals(feeList.size(), resultList.size());
        Assertions.assertEquals(feeList.get(0).toString(), resultList.get(0).toString());
        Assertions.assertEquals(feeList.get(1).toString(), resultList.get(1).toString());
    }
}