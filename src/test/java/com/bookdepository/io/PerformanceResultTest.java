package com.bookdepository.io;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the PerformanceResult class.
 */
public class PerformanceResultTest {

    @Test
    public void testConstructor() {
        PerformanceResult result = new PerformanceResult(100, 50, 25);
        
        assertNotNull(result);
        assertEquals(100, result.getComparisons());
        assertEquals(50, result.getSwaps());
        assertEquals(25, result.getExecutionTime());
    }

    @Test
    public void testGetters() {
        PerformanceResult result = new PerformanceResult(200, 150, 100);
        
        assertEquals(200, result.getComparisons());
        assertEquals(150, result.getSwaps());
        assertEquals(100, result.getExecutionTime());
    }

    @Test
    public void testZeroValues() {
        PerformanceResult result = new PerformanceResult(0, 0, 0);
        
        assertEquals(0, result.getComparisons());
        assertEquals(0, result.getSwaps());
        assertEquals(0, result.getExecutionTime());
    }

    @Test
    public void testLargeValues() {
        PerformanceResult result = new PerformanceResult(1000000, 500000, 10000);
        
        assertEquals(1000000, result.getComparisons());
        assertEquals(500000, result.getSwaps());
        assertEquals(10000, result.getExecutionTime());
    }

    @Test
    public void testToString() {
        PerformanceResult result = new PerformanceResult(100, 50, 25);
        String str = result.toString();
        
        assertNotNull(str);
        assertTrue(str.contains("100") || str.contains("50") || str.contains("25"));
    }

    @Test
    public void testSetters() {
        PerformanceResult result = new PerformanceResult(10, 5, 1);
        
        result.setComparisons(20);
        result.setSwaps(10);
        result.setExecutionTimeMs(2);
        
        assertEquals(20, result.getComparisons());
        assertEquals(10, result.getSwaps());
        assertEquals(2, result.getExecutionTimeMs());
    }

    @Test
    public void testAdd() {
        PerformanceResult result1 = new PerformanceResult(10, 5, 1);
        PerformanceResult result2 = new PerformanceResult(20, 10, 2);
        
        result1.add(result2);
        
        assertEquals(30, result1.getComparisons());
        assertEquals(15, result1.getSwaps());
        assertEquals(3, result1.getExecutionTimeMs());
    }

    @Test
    public void testDifferentValues() {
        PerformanceResult result1 = new PerformanceResult(10, 5, 1);
        PerformanceResult result2 = new PerformanceResult(20, 10, 2);
        
        assertNotEquals(result1.getComparisons(), result2.getComparisons());
        assertNotEquals(result1.getSwaps(), result2.getSwaps());
        assertNotEquals(result1.getExecutionTime(), result2.getExecutionTime());
    }
}

