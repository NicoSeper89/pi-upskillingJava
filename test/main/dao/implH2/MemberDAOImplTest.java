package main.dao.implH2;

import main.dao.dto.MemberDTO;
import main.dao.dto.PersonDTO;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MemberDAOImplTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

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

    @Test
    void getAll_ShouldReturnListOfMembersDtos_WhenDataBaseHasData() throws SQLException {
        //GIVEN
        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO(1,
                "Juan",
                "Gomez",
                "A",
                "Moreno 340",
                "666-555-333",
                ""));
        memberList.add(new MemberDTO(2,
                "Facundo",
                "Rodriguez",
                "B",
                "Cordoba 2360",
                "444-555-313",
                "facurodriguez@hotmail.com"));

        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("name")).thenReturn("Juan", "Facundo");
        when(mockResultSet.getString("surname")).thenReturn("Gomez", "Rodriguez");
        when(mockResultSet.getString("category")).thenReturn("A", "B");
        when(mockResultSet.getString("address")).thenReturn("Moreno 340", "Cordoba 2360");
        when(mockResultSet.getString("phone")).thenReturn("666-555-333", "444-555-313");
        when(mockResultSet.getString("email")).thenReturn("", "facurodriguez@hotmail.com");

        //WHEN
        List<MemberDTO> resultList = memberDao.getAll();

        //THEN
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getString("name");
        verify(mockResultSet, times(2)).getString("surname");
        verify(mockResultSet, times(2)).getString("category");
        verify(mockResultSet, times(2)).getString("address");
        verify(mockResultSet, times(2)).getString("phone");
        verify(mockResultSet, times(2)).getString("email");

        //Assertions
        Assertions.assertEquals(memberList.size(), resultList.size());
        Assertions.assertEquals(memberList.get(0).getId(), resultList.get(0).getId());
        Assertions.assertEquals(memberList.get(1).getId(), resultList.get(1).getId());
    }
}