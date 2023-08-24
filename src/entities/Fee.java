package entities;

public class Fee {

    private Integer id;
    private Integer amount;
    private String generationDate;
    private Boolean paid;
    private Member owner;

    public Fee(Integer id, Integer amount, Member owner, String generationDate) {
        this.id = id;
        this.amount = amount;
        this.generationDate = generationDate;
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
