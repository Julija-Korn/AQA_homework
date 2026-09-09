package org.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;

public class TriangleAreaTest {
    private TriangleArea triangleArea;

    @BeforeMethod
    public void setUp() {
        triangleArea = new TriangleArea();
    }

    @Test
    public void testTriangleAreaValid() {
        assertEquals(triangleArea.calculate(5, 4), 10.0);
    }

    @Test
    public void testTriangleAreaWithDouble() {
        assertEquals(triangleArea.calculate(5, 3), 7.5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaBaseZeroThrowsException() {
        triangleArea.calculate(0, 5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaHeightNegativeThrowsException() {
        triangleArea.calculate(5, -2);
    }
}
