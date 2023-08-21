package entities;

import java.time.LocalDate;

public class Fee {

    private Integer id;
    private Integer amount;
    private Member Owner;
    private LocalDate issueDate;
    private Boolean paid;

    public Fee(Integer amount, Member owner, LocalDate issueDate) {
        this.amount = amount;
        this.Owner = owner;
        this.issueDate = issueDate;
        this.paid = false;
    }

    public Fee(Integer id, Integer amount, Member owner, LocalDate issueDate, Boolean paid) {
        this.id = id;
        this.amount = amount;
        Owner = owner;
        this.issueDate = issueDate;
        this.paid = paid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public Member getOwner() {
        return Owner;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
