package br.com.fiap.cardweb.dto;

import br.com.fiap.cardweb.entity.TransactionEntity;

public class TransactionCreateDTO {
    private String description;
    private double amount;

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
}
