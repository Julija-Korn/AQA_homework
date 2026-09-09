package org.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;

public class FactorialTest {
    private Factorial factorial;

    @BeforeMethod
    public void setUp() {
        factorial = new Factorial();
    }

    @Test
    public void testFactorialOfZero() {
        assertEquals(factorial.calculate(0), 1);
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(factorial.calculate(1), 1);
    }

    @Test
    public void testFactorialOfFive() {
        assertEquals(factorial.calculate(5), 120);
    }

    @Test
    public void testFactorialOfTen() {
        assertEquals(factorial.calculate(10), 3628800);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegativeThrowsException() {
        factorial.calculate(-5);
    }
}
