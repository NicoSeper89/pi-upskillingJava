package main.dao;

import main.dao.dto.FeeDTO;

import java.util.List;

public interface FeeDAO extends DAO<FeeDTO> {

    List<FeeDTO> getAllMemberFees(Integer memberId);

}
