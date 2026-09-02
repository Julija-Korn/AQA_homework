package org.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;

public class NumberComparatorTest {
    private NumberComparator comparator;

    @BeforeMethod
    public void setUp() {
        comparator = new NumberComparator();
    }

    @Test
    public void testCompareGreater() {
        assertEquals(comparator.compare(10, 5), 1);
    }

    @Test
    public void testCompareLess() {
        assertEquals(comparator.compare(3, 7), -1);
    }

    @Test
    public void testCompareEqual() {
        assertEquals(comparator.compare(5, 5), 0);
    }

    @Test
    public void testCompareWithMessageGreater() {
        assertEquals(comparator.compareWithMessage(10, 5), "10 > 5");
    }

    @Test
    public void testCompareWithMessageLess() {
        assertEquals(comparator.compareWithMessage(3, 7), "3 < 7");
    }

    @Test
    public void testCompareWithMessageEqual() {
        assertEquals(comparator.compareWithMessage(5, 5), "5 = 5");
    }
}
