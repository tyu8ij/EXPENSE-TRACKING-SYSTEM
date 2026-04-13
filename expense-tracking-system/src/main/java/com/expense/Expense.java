package com.expense;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Expense class represents a single expense entry
 */
public class Expense {
    private String id;
    private String category;
    private double amount;
    private String description;
    private LocalDate date;

    /**
     * Constructor for Expense
     */
    public Expense(String id, String category, double amount, String description, LocalDate date) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
        
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.amount, amount) == 0 && 
               Objects.equals(id, expense.id) &&
               Objects.equals(category, expense.category) &&
               Objects.equals(date, expense.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, amount, date);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
