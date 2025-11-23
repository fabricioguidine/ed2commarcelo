package com.bookdepository.experiments;

import com.bookdepository.model.Record;
import com.bookdepository.model.Author;
import com.bookdepository.structures.hashtable.AuthorHashTable;
import com.bookdepository.structures.hashtable.RecordHashTable;
import com.bookdepository.algorithms.sorting.HeapSort;
import com.bookdepository.io.FileReader;
import com.bookdepository.io.Part2OutputWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hash Table Performance Experiment
 * 
 * Implements a program that reads N random and distinct books and counts
 * how many times the same author appears within those N books using hash tables.
 * The program prints the most frequent authors.
 * 
 * Data Structures Used:
 * - Hash Table for Records: Stores book records using open addressing with double hashing
 * - Hash Table for Authors: Stores author information and their frequencies
 * 
 * @author Débora Duarte, Fabrício Guidine, Walkíria Garcia
 * @version 1.0
 */
public class HashTableExperiment {
    
    private static final int NUM_RECORDS = 100000;
    
    /**
     * Reads and returns the number of authors that should be displayed at the end of execution.
     * 
     * @return Integer input value.
     */
    private static int readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of top authors to display (N): ");
        int input = scanner.nextInt();
        scanner.close();
        return input;
    }
    
    /**
     * Verifies if any author code in a Record is equal to 0.
     * Code 0 means the author was not informed or is unknown.
     * 
     * @param record Record object to check.
     * @return boolean. True if code of one of the authors is equal to 0.
     */
    private static boolean hasInvalidAuthors(Record record) {
        if (record == null || record.getAuthors() == null) {
            return true;
        }
        
        for (int i = 0; i < record.getAuthors().size(); i++) {
            String authorCode = record.getAuthors().get(i);
            if (authorCode != null && authorCode.compareTo("0") == 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Stores the read Records in a hash table and searches for the authors of a record
     * in the authors hash table and determines how many times an author appears in the records.
     * 
     * @param recordHashTable Hash table that stores Record objects.
     * @param authorHashTable Hash table that stores Author objects.
     * @param datasetFile Name of the dataset CSV file.
     */
    private static void hashRecords(RecordHashTable recordHashTable, 
                                    AuthorHashTable authorHashTable, 
                                    String datasetFile) {
        Record[] records = new Record[NUM_RECORDS];
        
        System.out.println("Loading records from dataset...");
        FileReader.loadRandomRecords(records, NUM_RECORDS, datasetFile);
        System.out.println("Finished loading records.");
        
        System.out.println("Processing records and updating author frequencies...");
        int validCount = 0;
        
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null && 
                records[i].getId() != null && 
                !records[i].getId().equals("-1") &&
                !hasInvalidAuthors(records[i])) {
                
                recordHashTable.insert(records[i]);
                
                // Update author frequencies
                for (int b = 0; b < records[i].getAuthors().size(); b++) {
                    try {
                        String authorCodeStr = records[i].getAuthors().get(b);
                        if (authorCodeStr != null && !authorCodeStr.isEmpty()) {
                            int authorCode = Integer.parseInt(authorCodeStr);
                            Author author = authorHashTable.search(authorCode);
                            if (author != null) {
                                author.incrementFrequency();
                                validCount++;
                            }
                        }
                    } catch (NumberFormatException | NullPointerException ex) {
                        // Skip invalid author codes
                    }
                }
            }
            
            // Progress indicator
            if ((i + 1) % 10000 == 0) {
                System.out.println("Processed " + (i + 1) + " records...");
            }
        }
        
        System.out.println("Finished processing records. Valid author references: " + validCount);
    }
    
    /**
     * Stores the authors read from the authors.csv file in a hash table.
     * 
     * @param authorHashTable Hash table where Authors will be stored.
     * @param authors ArrayList of Author objects containing the authors to be stored in the table.
     */
    private static void hashAuthors(AuthorHashTable authorHashTable, ArrayList<Author> authors) {
        System.out.println("Storing authors in hash table...");
        for (int i = 0; i < authors.size(); i++) {
            authorHashTable.insert(authors.get(i));
        }
        System.out.println("Finished storing " + authors.size() + " authors.");
    }
    
    /**
     * Transfers authors from the hash table, already with determined frequencies, to an array
     * that will be sorted according to each author's frequency.
     * 
     * @param authorHashTable Hash table containing Authors to be sorted.
     * @param authorsArray Array of Author objects that will be sorted.
     */
    private static void determineMostFrequent(AuthorHashTable authorHashTable, Author[] authorsArray) {
        HeapSort heapSort = new HeapSort();
        
        int nullCount = 0;
        
        // Copy authors from hash table to array
        Author[] hashArray = authorHashTable.getHash();
        int index = 0;
        
        for (int i = 0; i < hashArray.length && index < authorsArray.length; i++) {
            if (hashArray[i] != null) {
                authorsArray[index] = hashArray[i];
                index++;
            } else {
                nullCount++;
            }
        }
        
        // Resize array to actual size
        Author[] temp = new Author[index];
        System.arraycopy(authorsArray, 0, temp, 0, index);
        authorsArray = temp;
        
        System.out.println("Total hash table size: " + hashArray.length + ", Null positions: " + nullCount);
        System.out.println("Sorting authors by frequency...");
        
        // Sort by frequency
        heapSort.sort(authorsArray);
        
        System.out.println("Finished sorting.");
    }
    
    /**
     * Runs the hash table experiment.
     * 
     * @param authorsFile Path to authors.csv file.
     * @param datasetFile Path to dataset CSV file.
     */
    public static void run(String authorsFile, String datasetFile) {
        // Read input
        int n = readInput();
        
        // Read authors
        System.out.println("Reading authors from " + authorsFile + "...");
        ArrayList<Author> authors = FileReader.readAuthors(authorsFile);
        System.out.println("Read " + authors.size() + " authors.");
        
        // Create hash tables
        int authorTableSize = FileReader.getNextPowerOf2(authors.size()) - 1;
        AuthorHashTable authorHashTable = new AuthorHashTable(authorTableSize);
        hashAuthors(authorHashTable, authors);
        
        int recordTableSize = FileReader.getNextPowerOf2(NUM_RECORDS) - 1;
        RecordHashTable recordHashTable = new RecordHashTable(recordTableSize);
        
        // Process records and update frequencies
        hashRecords(recordHashTable, authorHashTable, datasetFile);
        
        // Determine most frequent authors
        Author[] mostFrequent = new Author[authorTableSize];
        determineMostFrequent(authorHashTable, mostFrequent);
        
        // Write results
        Part2OutputWriter outputWriter = new Part2OutputWriter(n);
        int count = 0;
        int i = mostFrequent.length - 1;
        
        System.out.println("\nTop " + n + " Most Frequent Authors:");
        System.out.println("----------------------------------------");
        
        while (count < n && i >= 0) {
            if (mostFrequent[i] != null && mostFrequent[i].getFrequency() > 0) {
                String result = mostFrequent[i].getName() + " | " + mostFrequent[i].getFrequency();
                System.out.println((count + 1) + ". " + result);
                outputWriter.addResult(result);
                count++;
            }
            i--;
        }
        
        outputWriter.writeResults();
        System.out.println("\nResults saved to saidaPart2.txt");
    }
    
    /**
     * Main method for hash table experiment.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        run("data/authors.csv", "data/dataset_simp_sem_descricao.csv");
    }
}

