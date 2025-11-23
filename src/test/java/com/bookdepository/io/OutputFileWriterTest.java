package com.bookdepository.io;

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
 * JUnit tests for the OutputFileWriter class.
 */
public class OutputFileWriterTest {

    @Before
    public void setUp() {
        // Create output directory if it doesn't exist
        new File("output").mkdirs();
    }

    @After
    public void tearDown() {
        // Clean up test output file
        File testFile = new File("output/saida.txt");
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testClearOutput() throws IOException {
        // Create a file with content
        File testFile = new File("output/saida.txt");
        try (java.io.FileWriter fw = new java.io.FileWriter(testFile)) {
            fw.write("Some content\n");
        }

        assertTrue(testFile.exists());
        
        OutputFileWriter.clearOutput();
        
        assertTrue(testFile.exists());
        // File should be empty or cleared
    }

    @Test
    public void testWriteSortingResults() throws IOException {
        OutputFileWriter.clearOutput();
        
        List<PerformanceResult> results = new ArrayList<>();
        results.add(new PerformanceResult(100, 50, 10));
        results.add(new PerformanceResult(200, 100, 20));
        
        OutputFileWriter.writeSortingResults(results, "QuickSort");
        
        File outputFile = new File("output/saida.txt");
        assertTrue(outputFile.exists());
        
        String content = new String(Files.readAllBytes(outputFile.toPath()));
        assertTrue(content.contains("QuickSort"));
    }

    @Test
    public void testWriteSortingResultsMultipleAlgorithms() throws IOException {
        OutputFileWriter.clearOutput();
        
        List<PerformanceResult> results1 = new ArrayList<>();
        results1.add(new PerformanceResult(100, 50, 10));
        
        List<PerformanceResult> results2 = new ArrayList<>();
        results2.add(new PerformanceResult(150, 75, 15));
        
        OutputFileWriter.writeSortingResults(results1, "QuickSort");
        OutputFileWriter.writeSortingResults(results2, "HeapSort");
        
        File outputFile = new File("output/saida.txt");
        assertTrue(outputFile.exists());
        
        String content = new String(Files.readAllBytes(outputFile.toPath()));
        assertTrue(content.contains("QuickSort"));
        assertTrue(content.contains("HeapSort"));
    }

    @Test
    public void testWriteSortingResultsEmptyList() throws IOException {
        OutputFileWriter.clearOutput();
        
        List<PerformanceResult> results = new ArrayList<>();
        
        OutputFileWriter.writeSortingResults(results, "QuickSort");
        
        File outputFile = new File("output/saida.txt");
        assertTrue(outputFile.exists());
        
        String content = new String(Files.readAllBytes(outputFile.toPath()));
        assertTrue(content.contains("QuickSort"));
    }
}

