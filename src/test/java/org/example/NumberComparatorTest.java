package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberComparatorTest {
    private final NumberComparator comparator = new NumberComparator();

    @Test
    void testCompareGreater() {
        assertEquals(1, comparator.compare(10, 5));
    }

    @Test
    void testCompareLess () {
        assertEquals(-1, comparator.compare(3, 7));
    }

    @Test
    void testCompareEqual() {
        assertEquals(0, comparator.compare(5, 5));
    }

    @Test
    void testCompareWithMessageGreater() {
        assertEquals("10 > 5", comparator.compareWithMessage(10, 5));
    }

    @Test
    void testCompareWithMessageLess() {
        assertEquals("3 < 7", comparator.compareWithMessage(3, 7));
    }

    @Test
    void testCompareWithMessageEqual() {
        assertEquals("5 = 5", comparator.compareWithMessage(5, 5));
    }
}
