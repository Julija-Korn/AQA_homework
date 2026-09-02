package org.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {
    private ArithmeticOperations arithmetic;

    @BeforeMethod
    public void setUp() {
        arithmetic = new ArithmeticOperations();
    }

    @Test
    public void testAdd() {
        assertEquals(arithmetic.add(3, 4), 7);
        assertEquals(arithmetic.add(-2, 2), 0);
    }

    @Test
    public void testSubtract() {
        assertEquals(arithmetic.subtract(5, 3), 2);
        assertEquals(arithmetic.subtract(0, 5), -5);
    }

    @Test
    public void testMultiply() {
        assertEquals(arithmetic.multiply(4, 5), 20);
        assertEquals(arithmetic.multiply(0, 100), 0);
    }

    @Test
    public void testDivide() {
        assertEquals(arithmetic.divide(6, 2), 3.0);
        assertEquals(arithmetic.divide(5, 2), 2.5);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        arithmetic.divide(5, 0);
    }
}
