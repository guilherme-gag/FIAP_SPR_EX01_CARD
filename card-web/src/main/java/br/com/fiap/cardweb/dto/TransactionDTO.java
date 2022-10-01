package br.com.fiap.cardweb.dto;

import br.com.fiap.cardweb.entity.TransactionEntity;

public class TransactionDTO {

    private long id;

    private String description;

    private double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionDTO(TransactionEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.amount = entity.getAmount();
    }
}
