package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ArithmeticOperationsTest {
    private final ArithmeticOperations operations = new ArithmeticOperations();

    @Test
    void testAdd() {
        assertEquals(7, operations.add(4,3));
        assertEquals(0, operations.add(-2, 2));
    }

    @Test
    void testSubtract() {
        assertEquals(2, operations.subtract(5, 3));
        assertEquals(-6, operations.subtract(0, 6));
    }

    @Test
    void testMultiply() {
        assertEquals(18, operations.multiply(3, 6));
        assertEquals(0, operations.multiply(100, 0));
    }

    @Test
    void testDivide () {
        assertEquals(5.0, operations.divide(20, 4));
        assertEquals(2.5, operations.divide(10, 4));
    }

    @Test
    void testDivideByZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> operations.divide(5, 0));
    }
}
