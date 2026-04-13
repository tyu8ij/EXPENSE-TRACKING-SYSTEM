package com.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/**
 * JUnit tests for Expense class
 */
public class ExpenseTest {
    private Expense expense;

    @BeforeEach
    public void setUp() {
        expense = new Expense("E001", "Food", 50.0, "Lunch", LocalDate.now());
    }

    @Test
    public void testExpenseCreation() {
        assertNotNull(expense);
        assertEquals("E001", expense.getId());
        assertEquals("Food", expense.getCategory());
        assertEquals(50.0, expense.getAmount());
        assertEquals("Lunch", expense.getDescription());
    }

    @Test
    public void testNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Expense("E002", "Food", -50.0, "Lunch", LocalDate.now())
        );
    }

    @Test
    public void testEmptyCategoryThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Expense("E002", "", 50.0, "Lunch", LocalDate.now())
        );
    }

    @Test
    public void testNullCategoryThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Expense("E002", null, 50.0, "Lunch", LocalDate.now())
        );
    }

    @Test
    public void testSetAmount() {
        expense.setAmount(75.0);
        assertEquals(75.0, expense.getAmount());
    }

    @Test
    public void testSetNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            expense.setAmount(-50.0)
        );
    }

    @Test
    public void testSetCategory() {
        expense.setCategory("Transport");
        assertEquals("Transport", expense.getCategory());
    }

    @Test
    public void testSetEmptyCategoryThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            expense.setCategory("")
        );
    }

    @Test
    public void testExpenseEquality() {
        Expense expense2 = new Expense("E001", "Food", 50.0, "Lunch", LocalDate.now());
        assertEquals(expense, expense2);
    }

    @Test
    public void testExpenseToString() {
        String str = expense.toString();
        assertTrue(str.contains("E001"));
        assertTrue(str.contains("Food"));
        assertTrue(str.contains("50.0"));
    }
}
