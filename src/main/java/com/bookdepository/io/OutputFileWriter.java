package com.bookdepository.io;

import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.FormatterClosedException;
import java.io.FileNotFoundException;

/**
 * Class for writing performance results to output files.
 * 
 * @author Débora Duarte
 * @version 1.0
 */
public class OutputFileWriter {
    
    private PerformanceResult[] results;
    private int index;
    private int size;
    private Formatter file;
    
    /**
     * Opens the output file with the specified filename.
     * 
     * @param filename Name of the file to be created/opened.
     */
    private void open(String filename) {
        try {
            file = new Formatter(filename);
        } catch (SecurityException permissionError) {
            System.err.println("No permission to write to file");
            System.exit(1);
        } catch (FileNotFoundException fileNotFound) {
            System.err.println("File not found or file cannot be created");
            System.exit(1);
        }
    }
    
    /**
     * Constructor for the OutputFileWriter class.
     * 
     * @param size Integer that represents the total number of results.
     */
    public OutputFileWriter(int size) {
        this.size = size;
        this.index = 0;
        this.results = new PerformanceResult[size];
    }
    
    /**
     * Default constructor for the OutputFileWriter class.
     * Uses ArrayList internally for dynamic size.
     */
    public OutputFileWriter() {
        this.size = 0;
        this.index = 0;
        this.results = new PerformanceResult[100]; // Initial capacity
    }
    
    /**
     * Adds a new result to be written to the file later.
     * 
     * @param result PerformanceResult object that is the result to be added.
     */
    public void addResult(PerformanceResult result) {
        if (index < results.length) {
            results[index] = result;
            index++;
        }
    }
    
    /**
     * Function that calls the open function of the same class, formats the file for output and performs the writing.
     * Writes to the default "saida.txt" file.
     */
    public void writeResults() {
        writeResults("saida.txt");
    }
    
    /**
     * Function that calls the open function of the same class, formats the file for output and performs the writing.
     * 
     * @param filename Name of the file to write results to.
     */
    public void writeResults(String filename) {
        open(filename);
        
        try {
            System.out.println("Writing results to " + filename);
            
            // Write header
            if (results[0] != null && !results[0].getOperation().isEmpty()) {
                file.format("Index,Algorithm,Operation,Record Count,Comparisons,Swaps,Time\n");
                for (int j = 0; j < index; j++) {
                    if (results[j] != null) {
                        file.format("%d,%s,%s,%s,%s,%s,%s\n", 
                            j + 1, 
                            results[j].getAlgorithm(), 
                            results[j].getOperation(),
                            results[j].getRecordCount(), 
                            results[j].getComparisons(), 
                            results[j].getSwaps(), 
                            results[j].getTime());
                    }
                }
            } else {
                file.format("Index,Algorithm,Record Count,Comparisons,Swaps,Time\n");
                for (int j = 0; j < index; j++) {
                    if (results[j] != null) {
                        file.format("%d,%s,%s,%s,%s,%s\n", 
                            j + 1, 
                            results[j].getAlgorithm(), 
                            results[j].getRecordCount(), 
                            results[j].getComparisons(), 
                            results[j].getSwaps(), 
                            results[j].getTime());
                    }
                }
            }
            
            file.close();
        } catch (FormatterClosedException formatError) {
            System.err.println("Error writing to file");
            return;
        } catch (NoSuchElementException elementException) {
            System.err.println("Invalid input. For example, expected a string but got an integer");
        }
    }
    
    /**
     * Closes the file.
     */
    public void close() {
        if (file != null) {
            file.close();
        }
    }
}

