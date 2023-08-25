package entities;

public class Member extends Person {

    public Member(Integer id) {
        super(id);
    }

    public Member(Integer id, String name, String surname, String category, String address, String phone, String email) {
        super(id, name, surname, category, address, phone, email);
    }

    public Member(String name, String surname, String category, String address, String phone, String email) {
        super(name, surname, category, address, phone, email);
    }
}
