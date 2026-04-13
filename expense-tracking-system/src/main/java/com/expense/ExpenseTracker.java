package com.expense;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ExpenseTracker manages all expense operations and calculations
 */
public class ExpenseTracker {
    private List<Expense> expenses;
    private int expenseCounter;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
        this.expenseCounter = 0;
    }

    /**
     * Add a new expense
     */
    public Expense addExpense(String category, double amount, String description, LocalDate date) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        String expenseId = "EXP" + System.currentTimeMillis() + "_" + (++expenseCounter);
        Expense expense = new Expense(expenseId, category, amount, description, date);
        expenses.add(expense);
        return expense;
    }

    /**
     * Remove an expense by ID
     */
    public boolean removeExpense(String id) {
        return expenses.removeIf(exp -> exp.getId().equals(id));
    }

    /**
     * Get total expenses
     */
    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    /**
     * Get total expenses by category
     */
    public double getTotalByCategory(String category) {
        return expenses.stream()
                .filter(exp -> exp.getCategory().equalsIgnoreCase(category))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    /**
     * Get expenses by category
     */
    public List<Expense> getExpensesByCategory(String category) {
        return expenses.stream()
                .filter(exp -> exp.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Get expenses within a date range
     */
    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenses.stream()
                .filter(exp -> !exp.getDate().isBefore(startDate) && !exp.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    /**
     * Get total expenses for a specific month
     */
    public double getTotalByMonth(YearMonth month) {
        return expenses.stream()
                .filter(exp -> YearMonth.from(exp.getDate()).equals(month))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    /**
     * Get average expense amount
     */
    public double getAverageExpense() {
        if (expenses.isEmpty()) {
            return 0;
        }
        return getTotalExpenses() / expenses.size();
    }

    /**
     * Get category-wise breakdown
     */
    public Map<String, Double> getCategoryBreakdown() {
        return expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }

    /**
     * Get all expenses
     */
    public List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }

    /**
     * Get expense count
     */
    public int getExpenseCount() {
        return expenses.size();
    }

    /**
     * Get highest expense
     */
    public Expense getHighestExpense() {
        return expenses.stream()
                .max(Comparator.comparingDouble(Expense::getAmount))
                .orElse(null);
    }

    /**
     * Get lowest expense
     */
    public Expense getLowestExpense() {
        return expenses.stream()
                .min(Comparator.comparingDouble(Expense::getAmount))
                .orElse(null);
    }

    /**
     * Clear all expenses
     */
    public void clearAllExpenses() {
        expenses.clear();
    }

    /**
     * Update an expense
     */
    public boolean updateExpense(String id, String category, double amount, String description, LocalDate date) {
        for (Expense exp : expenses) {
            if (exp.getId().equals(id)) {
                exp.setCategory(category);
                exp.setAmount(amount);
                exp.setDescription(description);
                exp.setDate(date);
                return true;
            }
        }
        return false;
    }
}
