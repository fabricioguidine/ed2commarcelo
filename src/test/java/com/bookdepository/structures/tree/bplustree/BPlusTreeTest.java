package com.bookdepository.structures.tree.bplustree;

import com.bookdepository.model.Record;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * JUnit tests for the BPlusTree class.
 */
public class BPlusTreeTest {
    private BPlusTree tree;

    @Before
    public void setUp() {
        tree = new BPlusTree(2);
    }

    @Test
    public void testConstructor() {
        BPlusTree t = new BPlusTree(2);
        assertNotNull(t);
        assertEquals(0, t.getComparisons());
        assertEquals(0, t.getInsertions());
    }

    @Test
    public void testConstructorWithDifferentDegree() {
        BPlusTree t1 = new BPlusTree(2);
        BPlusTree t2 = new BPlusTree(20);
        
        assertNotNull(t1);
        assertNotNull(t2);
    }

    @Test
    public void testInsertSingleRecord() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        tree.insert(record);
        
        Record found = tree.search("id1");
        assertNotNull(found);
        assertEquals("id1", found.getId());
        assertEquals(1, tree.getInsertions());
    }

    @Test
    public void testInsertMultipleRecords() {
        for (int i = 0; i < 10; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        assertEquals(10, tree.getInsertions());
        
        // Verify all records can be found
        for (int i = 0; i < 10; i++) {
            Record found = tree.search("id" + i);
            assertNotNull(found);
            assertEquals("id" + i, found.getId());
        }
    }

    @Test
    public void testInsertNullRecord() {
        tree.insert(null);
        assertEquals(0, tree.getInsertions());
    }

    @Test
    public void testInsertRecordWithNullId() {
        Record record = new Record();
        record.setTitle("Book");
        tree.insert(record);
        assertEquals(0, tree.getInsertions());
    }

    @Test
    public void testSearchExistingRecord() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        tree.insert(record);
        
        Record found = tree.search("id1");
        assertNotNull(found);
        assertEquals("id1", found.getId());
        assertEquals("Book 1", found.getTitle());
    }

    @Test
    public void testSearchNonExistentRecord() {
        Record found = tree.search("nonexistent");
        assertNull(found);
    }

    @Test
    public void testSearchEmptyTree() {
        Record found = tree.search("id1");
        assertNull(found);
    }

    @Test
    public void testResetCounters() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        tree.insert(record);
        tree.search("id1");
        
        assertTrue(tree.getComparisons() >= 0);
        assertTrue(tree.getInsertions() > 0);
        
        tree.resetCounters();
        assertEquals(0, tree.getComparisons());
        assertEquals(0, tree.getInsertions());
    }

    @Test
    public void testPerformanceTracking() {
        tree.resetCounters();
        
        // Insert multiple records
        for (int i = 0; i < 50; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        assertEquals(50, tree.getInsertions());
        
        // Search for records
        for (int i = 0; i < 50; i++) {
            tree.search("id" + i);
        }
        
        assertTrue(tree.getComparisons() >= 0);
    }

    @Test
    public void testTreeHeight() {
        // Insert records and check height
        for (int i = 0; i < 20; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        int height = tree.getHeight();
        assertTrue(height >= 0);
    }

    @Test
    public void testTreeWithDegree20() {
        BPlusTree largeDegreeTree = new BPlusTree(20);
        
        // Insert many records
        for (int i = 0; i < 100; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            largeDegreeTree.insert(record);
        }
        
        assertEquals(100, largeDegreeTree.getInsertions());
        
        // Verify all records are accessible
        for (int i = 0; i < 100; i++) {
            Record found = largeDegreeTree.search("id" + i);
            assertNotNull(found);
            assertEquals("id" + i, found.getId());
        }
    }

    @Test
    public void testTreeSplitting() {
        // Insert enough records to trigger splitting (with degree 2)
        for (int i = 0; i < 20; i++) {
            Record record = new Record("id" + String.format("%03d", i), "Book " + i, 
                null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        // All records should still be searchable after splits
        for (int i = 0; i < 20; i++) {
            Record found = tree.search("id" + String.format("%03d", i));
            assertNotNull(found);
        }
    }

    @Test
    public void testGetRoot() {
        assertNotNull(tree.getRoot());
        
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        tree.insert(record);
        
        assertNotNull(tree.getRoot());
    }
}

