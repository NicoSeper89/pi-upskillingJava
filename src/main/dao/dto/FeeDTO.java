package main.dao.dto;

public class FeeDTO {

    private Integer id;
    private Integer amount;
    private String generatedDate;
    private Boolean paid;
    private MemberDTO owner;

    public FeeDTO(Integer id, Integer amount, String generatedDate, Boolean paid, MemberDTO owner) {
        this.id = id;
        this.amount = amount;
        this.generatedDate = generatedDate;
        this.paid = paid;
        this.owner = owner;
    }

    public FeeDTO(Integer amount, MemberDTO owner) {
        this.amount = amount;
        this.owner = owner;
        this.paid = false;
    }

    public FeeDTO(Integer id, Integer amount, Boolean paid) {
        this.id = id;
        this.amount = amount;
        this.paid = paid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {this.amount = amount;}

    public String getGeneratedDate() {
        return generatedDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public MemberDTO getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "FeeDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", generationDate='" + generatedDate + '\'' +
                ", paid=" + paid +
                ", owner=" + owner.getId() +
                '}';
    }
}
