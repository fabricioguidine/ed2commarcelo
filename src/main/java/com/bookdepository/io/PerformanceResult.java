package com.bookdepository.io;

/**
 * Represents a performance result for an algorithm or data structure operation.
 * Contains algorithm name, operation type, number of records, comparisons, swaps, and time.
 * 
 * @author Débora Duarte, Fabrício Guidine, Walkíria Garcia
 * @version 1.0
 */
public class PerformanceResult {
    
    private String algorithm;
    private String operation;
    private String recordCount;
    private String comparisons;
    private String swaps;
    private String time;
    
    /**
     * Empty constructor for the PerformanceResult class.
     */
    public PerformanceResult() {
    }
    
    /**
     * Constructor for the PerformanceResult class.
     * 
     * @param algorithm Name of the algorithm or data structure used.
     * @param recordCount Number of records processed.
     * @param comparisons Number of comparisons performed.
     * @param swaps Number of swaps/copies performed.
     * @param time Time taken (in milliseconds).
     */
    public PerformanceResult(String algorithm, String recordCount, int comparisons, int swaps, long time) {
        this.algorithm = algorithm;
        this.operation = "";
        this.recordCount = recordCount;
        this.comparisons = Integer.toString(comparisons);
        this.swaps = Integer.toString(swaps);
        this.time = Long.toString(time);
    }
    
    /**
     * Constructor for the PerformanceResult class with operation type.
     * 
     * @param algorithm Name of the algorithm or data structure used.
     * @param operation Type of operation (Insertion, Search, etc.).
     * @param recordCount Number of records processed.
     * @param comparisons Number of comparisons performed.
     * @param swaps Number of swaps/copies performed.
     * @param time Time taken (in milliseconds).
     */
    public PerformanceResult(String algorithm, String operation, String recordCount, 
                            int comparisons, int swaps, long time) {
        this.algorithm = algorithm;
        this.operation = operation;
        this.recordCount = recordCount;
        this.comparisons = Integer.toString(comparisons);
        this.swaps = Integer.toString(swaps);
        this.time = Long.toString(time);
    }
    
    /**
     * Returns the name of the algorithm used (e.g., QuickSort or HeapSort).
     * 
     * @return algorithm as a String.
     */
    public String getAlgorithm() {
        return algorithm;
    }
    
    /**
     * Returns the operation type (Insertion, Search, etc.).
     * 
     * @return operation as a String.
     */
    public String getOperation() {
        return operation;
    }
    
    /**
     * Returns the number of records processed.
     * 
     * @return recordCount as a String.
     */
    public String getRecordCount() {
        return recordCount;
    }
    
    /**
     * Returns the number of comparisons performed.
     * 
     * @return comparisons as a String.
     */
    public String getComparisons() {
        return comparisons;
    }
    
    /**
     * Returns the number of swaps.
     * 
     * @return swaps as a String.
     */
    public String getSwaps() {
        return swaps;
    }
    
    /**
     * Returns the time taken to perform the operation.
     * 
     * @return time as a String.
     */
    public String getTime() {
        return time;
    }
}

