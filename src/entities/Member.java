package entities;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private Integer id;
    private String name;
    private String surname;
    private Character category;
    private String address;
    private String phone;
    private String email;
    private List<Fee> fees;

    public Member(Integer id, String name, String surname, String address, String phone, String email, List<Fee> fees) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.fees = fees;
    }

    public Member(String name, String surname, String address, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

    public Character getCategory() {
        return category;
    }

    public void setCategory(Character category) {
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

}
