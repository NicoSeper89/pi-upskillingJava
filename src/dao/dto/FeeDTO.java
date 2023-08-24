package dao.dto;

public class FeeDTO {

    private Integer id;
    private final Integer amount;
    private String generationDate;
    private Boolean paid;
    private MemberDTO owner;

    public FeeDTO(Integer id, Integer amount, String generationDate, MemberDTO owner) {
        this.id = id;
        this.amount = amount;
        this.generationDate = generationDate;
        this.owner = owner;
        this.paid = false;
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

    public String getGenerationDate() {
        return generationDate;
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



}
