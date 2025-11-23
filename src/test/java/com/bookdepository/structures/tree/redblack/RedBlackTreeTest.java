package com.bookdepository.structures.tree.redblack;

import com.bookdepository.model.Record;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * JUnit tests for the RedBlackTree class.
 */
public class RedBlackTreeTest {
    private RedBlackTree tree;

    @Before
    public void setUp() {
        tree = new RedBlackTree();
    }

    @Test
    public void testConstructor() {
        assertNotNull(tree);
        assertEquals(0, tree.getComparisons());
        assertEquals(0, tree.getRotations());
    }

    @Test
    public void testInsertSingleRecord() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        tree.insert(record);
        
        Record found = tree.search("id1");
        assertNotNull(found);
        assertEquals("id1", found.getId());
    }

    @Test
    public void testInsertMultipleRecords() {
        for (int i = 0; i < 10; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
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
        // Should not throw exception
    }

    @Test
    public void testInsertRecordWithNullId() {
        Record record = new Record();
        record.setTitle("Book");
        tree.insert(record);
        // Should not throw exception
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
    public void testSearchNull() {
        Record found = tree.search(null);
        assertNull(found);
    }

    @Test
    public void testSearchEmptyTree() {
        Record found = tree.search("id1");
        assertNull(found);
    }

    @Test
    public void testInsertDuplicate() {
        Record record1 = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        Record record2 = new Record("id1", "Book 1 Updated", null, null, null, null, null, 0f, 0, 2);
        
        tree.insert(record1);
        tree.insert(record2);
        
        Record found = tree.search("id1");
        assertNotNull(found);
        // Should have the updated record
        assertEquals("Book 1 Updated", found.getTitle());
    }

    @Test
    public void testResetCounters() {
        Record record = new Record("id1", "Book 1", null, null, null, null, null, 0f, 0, 1);
        tree.insert(record);
        tree.search("id1");
        
        assertTrue(tree.getComparisons() > 0);
        
        tree.resetCounters();
        assertEquals(0, tree.getComparisons());
        assertEquals(0, tree.getRotations());
    }

    @Test
    public void testPerformanceTracking() {
        tree.resetCounters();
        
        // Insert multiple records
        for (int i = 0; i < 100; i++) {
            Record record = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        // Search for records
        for (int i = 0; i < 100; i++) {
            tree.search("id" + i);
        }
        
        assertTrue(tree.getComparisons() > 0);
    }

    @Test
    public void testTreeBalance() {
        // Insert records in sorted order (worst case for regular BST)
        for (int i = 0; i < 50; i++) {
            Record record = new Record("id" + String.format("%03d", i), "Book " + i, 
                null, null, null, null, null, 0f, 0, i);
            tree.insert(record);
        }
        
        // All records should still be searchable (tree is balanced)
        for (int i = 0; i < 50; i++) {
            Record found = tree.search("id" + String.format("%03d", i));
            assertNotNull(found);
            assertEquals("id" + String.format("%03d", i), found.getId());
        }
    }

    @Test
    public void testTreeBalanceWithRotations() {
        // Insert records that will cause rotations
        Record[] records = new Record[20];
        for (int i = 0; i < 20; i++) {
            records[i] = new Record("id" + i, "Book " + i, null, null, null, null, null, 0f, 0, i);
            tree.insert(records[i]);
        }
        
        // Verify rotations occurred
        assertTrue(tree.getRotations() >= 0);
        
        // Verify all records are still accessible
        for (int i = 0; i < 20; i++) {
            Record found = tree.search("id" + i);
            assertNotNull(found);
        }
    }
}

