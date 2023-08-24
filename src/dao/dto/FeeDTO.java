package dao.dto;

public class FeeDTO {

    private Integer id;
    private Integer amount;
    private String generationDate;
    private Boolean paid;
    private MemberDTO owner;

    public FeeDTO(Integer id, Integer amount, MemberDTO owner) {
        this.id = id;
        this.amount = amount;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public MemberDTO getOwner() {
        return owner;
    }

}
