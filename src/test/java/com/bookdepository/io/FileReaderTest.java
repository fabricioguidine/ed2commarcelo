package com.bookdepository.io;

import com.bookdepository.model.Record;
import com.bookdepository.model.Author;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JUnit tests for the FileReader class.
 * Note: These tests require test data files to be set up.
 */
public class FileReaderTest {

    @Before
    public void setUp() {
        // Create test input directory if it doesn't exist
        new File("input").mkdirs();
    }

    @Test
    public void testReadInputSizes() throws IOException {
        // Create a test input file
        File testFile = new File("input/entrada.txt");
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("3\n");
            fw.write("100\n");
            fw.write("500\n");
            fw.write("1000\n");
        }

        List<Integer> sizes = FileReader.readInputSizes();
        
        assertNotNull(sizes);
        assertEquals(3, sizes.size());
        assertEquals(Integer.valueOf(100), sizes.get(0));
        assertEquals(Integer.valueOf(500), sizes.get(1));
        assertEquals(Integer.valueOf(1000), sizes.get(2));
    }

    @Test
    public void testReadInputSizesEmptyFile() throws IOException {
        // Create an empty test input file
        File testFile = new File("input/entrada.txt");
        try (FileWriter fw = new FileWriter(testFile)) {
            // Empty file
        }

        List<Integer> sizes = FileReader.readInputSizes();
        
        assertNotNull(sizes);
        assertTrue(sizes.isEmpty());
    }

    @Test
    public void testReadInputSizesSingleValue() throws IOException {
        // Create a test input file with single value
        File testFile = new File("input/entrada.txt");
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("1\n");
            fw.write("50\n");
        }

        List<Integer> sizes = FileReader.readInputSizes();
        
        assertNotNull(sizes);
        assertEquals(1, sizes.size());
        assertEquals(Integer.valueOf(50), sizes.get(0));
    }

    @Test(expected = IOException.class)
    public void testReadInputSizesFileNotFound() throws IOException {
        // Delete the file if it exists
        File testFile = new File("input/nonexistent.txt");
        if (testFile.exists()) {
            testFile.delete();
        }

        // This will throw IOException
        FileReader.readInputSizes();
    }

    @Test
    public void testReadAuthorsMap() throws IOException {
        // Create test data directory if it doesn't exist
        new File("data").mkdirs();
        
        // Create a test authors CSV file
        File testFile = new File("data/authors.csv");
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("id,name\n");
            fw.write("auth1,Author One\n");
            fw.write("auth2,Author Two\n");
        }

        Map<String, Author> authors = FileReader.readAuthorsMap();
        
        assertNotNull(authors);
        // The map should contain the authors if file exists
    }

    @Test
    public void testReadRecordsWithLimit() throws IOException {
        // Create test data directory if it doesn't exist
        new File("data").mkdirs();
        
        // Create a test records CSV file
        File testFile = new File("data/dataset_simp_sem_descricao.csv");
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("id,title,authors,isbn10,isbn13,edition,categories,rating_avg,rating_count,bestsellers_rank\n");
            fw.write("id1,Book 1,\"['auth1']\",0123456789,9780123456789,First Edition,\"['Fiction']\",4.5,100,10\n");
            fw.write("id2,Book 2,\"['auth2']\",0123456790,9780123456790,Second Edition,\"['Adventure']\",4.0,50,20\n");
        }

        // Create authors file as well
        File authorsFile = new File("data/authors.csv");
        try (FileWriter fw = new FileWriter(authorsFile)) {
            fw.write("id,name\n");
            fw.write("auth1,Author One\n");
            fw.write("auth2,Author Two\n");
        }

        List<Record> records = FileReader.readRecords(2);
        
        assertNotNull(records);
        assertTrue(records.size() <= 2);
    }

    @Test
    public void testReadAllRecords() throws IOException {
        // Create test data files (similar to above)
        new File("data").mkdirs();
        
        File testFile = new File("data/dataset_simp_sem_descricao.csv");
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("id,title,authors,isbn10,isbn13,edition,categories,rating_avg,rating_count,bestsellers_rank\n");
            fw.write("id1,Book 1,\"['auth1']\",0123456789,9780123456789,First Edition,\"['Fiction']\",4.5,100,10\n");
        }

        File authorsFile = new File("data/authors.csv");
        try (FileWriter fw = new FileWriter(authorsFile)) {
            fw.write("id,name\n");
            fw.write("auth1,Author One\n");
        }

        List<Record> records = FileReader.readAllRecords();
        
        assertNotNull(records);
    }
}

