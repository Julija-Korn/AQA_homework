package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {
    private final Factorial factorial = new Factorial();

    @Test
    void testFactorialOfZero() {
        assertEquals(1, factorial.calculate(0));
    }

    @Test
    void testFactorialOfOne() {
        assertEquals(1, factorial.calculate(1));
    }

    @Test
    void testFactorialOfFive() {
        assertEquals(120, factorial.calculate(5));
    }

    @Test
    void testFactorialOfTen() {
        assertEquals(3628800, factorial.calculate(10));
    }

    @Test
    void testFactorialNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> factorial.calculate(-5));
    }
}
