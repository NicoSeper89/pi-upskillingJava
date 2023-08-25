package dao.dto;

public class MemberDTO extends PersonDTO{

    public MemberDTO(Integer id, String name, String surname, String category, String address, String phone, String email) {
        super(id, name, surname, category, address, phone, email);
    }

    public MemberDTO(String name, String surname, String category, String address, String phone, String email) {
        super(name, surname, category, address, phone, email);
    }

    public MemberDTO(Integer id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Member: " + super.toString();
    }
}
