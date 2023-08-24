package dao.dto;

public class MemberDTO {
    private Integer id;
    private String name;
    private String surname;
    private String category;
    private String address;
    private String phone;
    private String email;

    public MemberDTO(Integer id, String name, String surname, String category, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.category = category;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public MemberDTO(String name, String surname, String category, String address, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.category = category;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public MemberDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", category='" + category + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
