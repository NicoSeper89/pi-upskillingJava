package dao.implH2;

import entities.Member;

import java.sql.Connection;
import java.util.List;

public class MemberDAO implements dao.MemberDAO {

    private Connection conn;

    public MemberDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Member member) {

    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void delete(Member member) {

    }

    @Override
    public List<Member> getAll() {
        return null;
    }

    @Override
    public Member getByID(Integer id) {
        return null;
    }
}
