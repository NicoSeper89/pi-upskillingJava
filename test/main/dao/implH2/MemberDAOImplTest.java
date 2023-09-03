package main.dao.implH2;

import main.dao.dto.MemberDTO;
import main.dao.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MemberDAOImplTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;

    @InjectMocks
    private MemberDAOImpl memberDao;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    void insert_ShouldInsertMember_WhenValidMemberDto() throws SQLException {

        // GIVEN
        PersonDTO memberDTO = new MemberDTO(
                "Juan",
                "Gomez",
                "A",
                "Moreno 54",
                "555-453-458",
                "juangomez@hotmail.com"
        );

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // WHEN
        memberDao.insert(memberDTO);

        // THEN
        verify(mockPreparedStatement).setString(1, memberDTO.getName());
        verify(mockPreparedStatement).setString(2, memberDTO.getSurname());
        verify(mockPreparedStatement).setString(3, memberDTO.getCategory());
        verify(mockPreparedStatement).setString(4, memberDTO.getAddress());
        verify(mockPreparedStatement).setString(5, memberDTO.getPhone());
        verify(mockPreparedStatement).setString(6, memberDTO.getEmail());
        verify(mockPreparedStatement).executeUpdate();

    }

    @Test
    void update_ShouldUpdateMember_WhenValidMemberDto() throws SQLException {

        // GIVEN
        PersonDTO memberDTO = new MemberDTO(
                1,
                "Juan",
                "Gomez",
                "A",
                "Moreno 54",
                "555-453-458",
                "juangomez@hotmail.com"
        );

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // WHEN
        memberDao.update(memberDTO);

        // THEN
        verify(mockPreparedStatement).setString(1, memberDTO.getName());
        verify(mockPreparedStatement).setString(2, memberDTO.getSurname());
        verify(mockPreparedStatement).setString(3, memberDTO.getCategory());
        verify(mockPreparedStatement).setString(4, memberDTO.getAddress());
        verify(mockPreparedStatement).setString(5, memberDTO.getPhone());
        verify(mockPreparedStatement).setString(6, memberDTO.getEmail());
        verify(mockPreparedStatement).setInt(7, memberDTO.getId());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void delete_ShouldDeleteMember_WhenValidId() throws SQLException {
        // GIVEN
        int memberId = 1;

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // WHEN
        memberDao.delete(memberId);

        // THEN
        verify(mockPreparedStatement).setInt(1, memberId);
        verify(mockPreparedStatement).executeUpdate();
    }

}