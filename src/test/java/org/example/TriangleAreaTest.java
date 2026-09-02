package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TriangleAreaTest {
    private final TriangleArea triangleArea = new TriangleArea();

    @Test
    void testTriangleAreaValid() {
        assertEquals(10, triangleArea.calculate(5, 4));
    }

    @Test
    void testTriangleAreaWithDouble() {
        assertEquals(7.5, 5,3);
    }

    @Test
    void testTriangleAreaBaseZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> triangleArea.calculate(0, 5));
    }

    @Test
    void testTriangleAreaHeightNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> triangleArea.calculate(5, -2));
    }
}

