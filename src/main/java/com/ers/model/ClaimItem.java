package com.ers.model;

import java.time.LocalDate;

public class ClaimItem {
    private int itemId;
    private int claimId;
    private int categoryId;
    private String description;
    private double amount;
    private LocalDate expenseDate;

    public ClaimItem(int claimId, int categoryId, String description, double amount, LocalDate expenseDate) {
        this.claimId = claimId;
        this.categoryId = categoryId;
        this.description = description;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    @Override
    public String toString() {
        return "ClaimItems{" +
                "itemId=" + itemId +
                ", claimId=" + claimId +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", expenseDate=" + expenseDate +
                '}';
    }
}
