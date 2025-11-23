package com.bookdepository.io;

import com.bookdepository.model.Author;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit tests for the Part2OutputWriter class.
 */
public class Part2OutputWriterTest {

    @Before
    public void setUp() {
        // Create output directory if it doesn't exist
        new File("output").mkdirs();
    }

    @After
    public void tearDown() {
        // Clean up test output file
        File testFile = new File("output/saidaPart2.txt");
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testWriteMostFrequentAuthors() throws IOException {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("auth1", "Author One", 10));
        authors.add(new Author("auth2", "Author Two", 5));
        authors.add(new Author("auth3", "Author Three", 3));
        
        Part2OutputWriter.writeMostFrequentAuthors(authors, 3);
        
        File outputFile = new File("output/saidaPart2.txt");
        assertTrue(outputFile.exists());
        
        String content = new String(Files.readAllBytes(outputFile.toPath()));
        assertTrue(content.contains("Author One") || content.contains("auth1"));
    }

    @Test
    public void testWriteMostFrequentAuthorsEmptyList() throws IOException {
        List<Author> authors = new ArrayList<>();
        
        Part2OutputWriter.writeMostFrequentAuthors(authors, 10);
        
        File outputFile = new File("output/saidaPart2.txt");
        assertTrue(outputFile.exists());
    }

    @Test
    public void testWriteMostFrequentAuthorsSingleAuthor() throws IOException {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("auth1", "Single Author", 1));
        
        Part2OutputWriter.writeMostFrequentAuthors(authors, 1);
        
        File outputFile = new File("output/saidaPart2.txt");
        assertTrue(outputFile.exists());
        
        String content = new String(Files.readAllBytes(outputFile.toPath()));
        assertTrue(content.length() > 0);
    }

    @Test
    public void testWriteMostFrequentAuthorsLargeList() throws IOException {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            authors.add(new Author("auth" + i, "Author " + i, i));
        }
        
        Part2OutputWriter.writeMostFrequentAuthors(authors, 50);
        
        File outputFile = new File("output/saidaPart2.txt");
        assertTrue(outputFile.exists());
    }

    @Test
    public void testWriteMostFrequentAuthorsTopN() throws IOException {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            authors.add(new Author("auth" + i, "Author " + i, 10 - i));
        }
        
        Part2OutputWriter.writeMostFrequentAuthors(authors, 5);
        
        File outputFile = new File("output/saidaPart2.txt");
        assertTrue(outputFile.exists());
        
        String content = new String(Files.readAllBytes(outputFile.toPath()));
        // Should only have 5 authors
        int count = content.split("\n").length;
        assertTrue(count <= 5);
    }
}

