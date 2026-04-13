package com.expense;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Main class to demonstrate the Expense Tracking System
 */
public class Main {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        LocalDate today = LocalDate.now();

        System.out.println("=== Expense Tracking System ===\n");

        // Add some expenses
        System.out.println("Adding expenses...");
        tracker.addExpense("Food", 45.50, "Lunch at restaurant", today);
        tracker.addExpense("Transport", 15.00, "Taxi fare", today);
        tracker.addExpense("Food", 12.50, "Coffee and snacks", today);
        tracker.addExpense("Entertainment", 50.00, "Movie tickets", today);
        tracker.addExpense("Utilities", 100.00, "Electricity bill", today);

        System.out.println("\nTotal Expenses: $" + String.format("%.2f", tracker.getTotalExpenses()));
        System.out.println("Number of Expenses: " + tracker.getExpenseCount());
        System.out.println("Average Expense: $" + String.format("%.2f", tracker.getAverageExpense()));

        System.out.println("\n--- Category Breakdown ---");
        tracker.getCategoryBreakdown().forEach((category, total) ->
            System.out.println(category + ": $" + String.format("%.2f", total))
        );

        System.out.println("\n--- Expense Details ---");
        tracker.getAllExpenses().forEach(System.out::println);

        System.out.println("\n--- Highest Expense ---");
        Expense highest = tracker.getHighestExpense();
        if (highest != null) {
            System.out.println(highest);
        }

        System.out.println("\n--- Lowest Expense ---");
        Expense lowest = tracker.getLowestExpense();
        if (lowest != null) {
            System.out.println(lowest);
        }

        System.out.println("\n=== System Ready ===");
    }
}
