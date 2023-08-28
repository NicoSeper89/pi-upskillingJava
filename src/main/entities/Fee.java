package main.entities;

public class Fee {

    private Integer id;
    private Integer amount;
    private String generationDate;
    private Boolean paid;
    private Member owner;

    public Fee(Integer id, Integer amount, String generationDate, Member owner) {
        this.id = id;
        this.amount = amount;
        this.generationDate = generationDate;
        this.owner = owner;
        this.paid = false;
    }

    public Fee(Integer id, Integer amount, Boolean paid) {
        this.id = id;
        this.amount = amount;
        this.paid = paid;
    }

    public Fee(Integer amount, Member owner) {
        this.amount = amount;
        this.owner = owner;
        this.paid = false;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public Member getOwner() {
        return owner;
    }

    public String getGenerationDate() {
        return generationDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
