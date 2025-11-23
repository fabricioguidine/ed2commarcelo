package com.bookdepository.io;

import java.util.Formatter;
import java.util.FormatterClosedException;
import java.io.FileNotFoundException;

/**
 * Output file writer for Part 2 results.
 * Writes the most frequent authors to an output file.
 * 
 * @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
 * @version 1.0
 */
public class Part2OutputWriter {
    
    private String[] results;
    private int index;
    private int size;
    private Formatter file;
    
    /**
     * Opens the output file.
     */
    private void open() {
        try {
            file = new Formatter("saidaPart2.txt");
        } catch (SecurityException permissionError) {
            System.err.println("No permission to write to file");
            System.exit(1);
        } catch (FileNotFoundException fileNotFound) {
            System.err.println("File not found or file cannot be created");
            System.exit(1);
        }
    }
    
    /**
     * Constructor for the Part2OutputWriter class.
     * 
     * @param size Integer that represents the total number of results (top N authors).
     */
    public Part2OutputWriter(int size) {
        this.size = size;
        this.index = 0;
        this.results = new String[size];
    }
    
    /**
     * Adds a new result to be written to the file later.
     * 
     * @param result String that is the result to be added.
     */
    public void addResult(String result) {
        if (index < size) {
            results[index] = result;
            index++;
        }
    }
    
    /**
     * Function that calls the open function, formats the file for output and performs the writing.
     */
    public void writeResults() {
        open();
        try {
            System.out.println("Writing results to saidaPart2.txt");
            file.format("Top %d Most Frequent Authors\n", size);
            file.format("Rank,Author Name | Frequency\n");
            
            for (int j = 0; j < index; j++) {
                if (results[j] != null) {
                    file.format("%d,%s\n", j + 1, results[j]);
                }
            }
            
            file.close();
        } catch (FormatterClosedException formatError) {
            System.err.println("Error writing to file");
            return;
        }
    }
}

