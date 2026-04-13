package com.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

/**
 * JUnit tests for ExpenseTracker class
 */
public class ExpenseTrackerTest {
    private ExpenseTracker tracker;
    private LocalDate today;

    @BeforeEach
    public void setUp() {
        tracker = new ExpenseTracker();
        today = LocalDate.now();
    }

    @Test
    public void testAddExpense() {
        Expense expense = tracker.addExpense("Food", 50.0, "Lunch", today);
        assertNotNull(expense);
        assertEquals(1, tracker.getExpenseCount());
        assertEquals("Food", expense.getCategory());
        assertEquals(50.0, expense.getAmount());
    }

    @Test
    public void testAddNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            tracker.addExpense("Food", -50.0, "Lunch", today)
        );
    }

    @Test
    public void testRemoveExpense() {
        Expense expense = tracker.addExpense("Food", 50.0, "Lunch", today);
        assertTrue(tracker.removeExpense(expense.getId()));
        assertEquals(0, tracker.getExpenseCount());
    }

    @Test
    public void testRemoveNonExistentExpense() {
        assertFalse(tracker.removeExpense("NonExistent"));
    }

    @Test
    public void testGetTotalExpenses() {
        tracker.addExpense("Food", 50.0, "Lunch", today);
        tracker.addExpense("Transport", 30.0, "Taxi", today);
        tracker.addExpense("Entertainment", 20.0, "Movie", today);
        
        assertEquals(100.0, tracker.getTotalExpenses());
    }

    @Test
    public void testGetTotalByCategory() {
        tracker.addExpense("Food", 50.0, "Lunch", today);
        tracker.addExpense("Food", 30.0, "Dinner", today);
        tracker.addExpense("Transport", 20.0, "Taxi", today);
        
        assertEquals(80.0, tracker.getTotalByCategory("Food"));
        assertEquals(20.0, tracker.getTotalByCategory("Transport"));
    }

    @Test
    public void testGetExpensesByCategory() {
        tracker.addExpense("Food", 50.0, "Lunch", today);
        tracker.addExpense("Food", 30.0, "Dinner", today);
        tracker.addExpense("Transport", 20.0, "Taxi", today);
        
        List<Expense> foodExpenses = tracker.getExpensesByCategory("Food");
        assertEquals(2, foodExpenses.size());
    }

    @Test
    public void testGetExpensesByDateRange() {
        LocalDate date1 = LocalDate.of(2024, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 1, 15);
        LocalDate date3 = LocalDate.of(2024, 2, 1);
        
        tracker.addExpense("Food", 50.0, "Lunch", date1);
        tracker.addExpense("Transport", 30.0, "Taxi", date2);
        tracker.addExpense("Entertainment", 20.0, "Movie", date3);
        
        List<Expense> expenses = tracker.getExpensesByDateRange(
            LocalDate.of(2024, 1, 1), 
            LocalDate.of(2024, 1, 31)
        );
        assertEquals(2, expenses.size());
    }

    @Test
    public void testGetAverageExpense() {
        tracker.addExpense("Food", 30.0, "Lunch", today);
        tracker.addExpense("Transport", 60.0, "Taxi", today);
        tracker.addExpense("Entertainment", 90.0, "Movie", today);
        
        assertEquals(60.0, tracker.getAverageExpense());
    }

    @Test
    public void testGetAverageExpenseWhenEmpty() {
        assertEquals(0, tracker.getAverageExpense());
    }

    @Test
    public void testGetCategoryBreakdown() {
        tracker.addExpense("Food", 50.0, "Lunch", today);
        tracker.addExpense("Food", 30.0, "Dinner", today);
        tracker.addExpense("Transport", 20.0, "Taxi", today);
        
        Map<String, Double> breakdown = tracker.getCategoryBreakdown();
        assertEquals(80.0, breakdown.get("Food"));
        assertEquals(20.0, breakdown.get("Transport"));
        assertEquals(2, breakdown.size());
    }

    @Test
    public void testGetHighestExpense() {
        tracker.addExpense("Food", 50.0, "Lunch", today);
        tracker.addExpense("Transport", 100.0, "Flight", today);
        tracker.addExpense("Entertainment", 20.0, "Movie", today);
        
        Expense highest = tracker.getHighestExpense();
        assertEquals(100.0, highest.getAmount());
        assertEquals("Transport", highest.getCategory());
    }

    @Test
    public void testGetLowestExpense() {
        tracker.addExpense("Food", 50.0, "Lunch", today);
        tracker.addExpense("Transport", 100.0, "Flight", today);
        tracker.addExpense("Entertainment", 20.0, "Movie", today);
        
        Expense lowest = tracker.getLowestExpense();
        assertEquals(20.0, lowest.getAmount());
        assertEquals("Entertainment", lowest.getCategory());
    }

    @Test
    public void testGetAllExpenses() {
        tracker.addExpense("Food", 50.0, "Lunch", today);
        tracker.addExpense("Transport", 30.0, "Taxi", today);
        
        List<Expense> all = tracker.getAllExpenses();
        assertEquals(2, all.size());
    }

    @Test
    public void testClearAllExpenses() {
        tracker.addExpense("Food", 50.0, "Lunch", today);
        tracker.addExpense("Transport", 30.0, "Taxi", today);
        
        tracker.clearAllExpenses();
        assertEquals(0, tracker.getExpenseCount());
    }

    @Test
    public void testUpdateExpense() {
        Expense expense = tracker.addExpense("Food", 50.0, "Lunch", today);
        
        boolean updated = tracker.updateExpense(expense.getId(), "Transport", 75.0, "Taxi", today);
        assertTrue(updated);
        assertEquals(75.0, tracker.getTotalByCategory("Transport"));
    }

    @Test
    public void testGetTotalByMonth() {
        LocalDate jan1 = LocalDate.of(2024, 1, 1);
        LocalDate jan15 = LocalDate.of(2024, 1, 15);
        LocalDate feb1 = LocalDate.of(2024, 2, 1);
        
        tracker.addExpense("Food", 50.0, "Lunch", jan1);
        tracker.addExpense("Transport", 30.0, "Taxi", jan15);
        tracker.addExpense("Entertainment", 20.0, "Movie", feb1);
        
        YearMonth janMonth = YearMonth.of(2024, 1);
        YearMonth febMonth = YearMonth.of(2024, 2);
        
        assertEquals(80.0, tracker.getTotalByMonth(janMonth));
        assertEquals(20.0, tracker.getTotalByMonth(febMonth));
    }

    @Test
    public void testMultipleOperations() {
        // Add expenses
        Expense exp1 = tracker.addExpense("Food", 50.0, "Lunch", today);
        Expense exp2 = tracker.addExpense("Transport", 30.0, "Taxi", today);
        Expense exp3 = tracker.addExpense("Food", 20.0, "Coffee", today);
        
        assertEquals(3, tracker.getExpenseCount());
        assertEquals(100.0, tracker.getTotalExpenses());
        
        // Remove one
        tracker.removeExpense(exp2.getId());
        assertEquals(2, tracker.getExpenseCount());
        assertEquals(70.0, tracker.getTotalExpenses());
        
        // Update one
        tracker.updateExpense(exp1.getId(), "Dining", 60.0, "Dinner", today);
        assertEquals(80.0, tracker.getTotalExpenses());
    }
}
