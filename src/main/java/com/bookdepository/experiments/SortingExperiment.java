package com.bookdepository.experiments;

import com.bookdepository.model.Record;
import com.bookdepository.algorithms.sorting.QuickSort;
import com.bookdepository.algorithms.sorting.HeapSort;
import com.bookdepository.io.FileReader;
import com.bookdepository.io.OutputFileWriter;
import com.bookdepository.io.PerformanceResult;
import java.io.IOException;

/**
 * Sorting Algorithm Performance Experiment
 * 
 * This experiment analyzes the performance of sorting algorithms on book records.
 * It implements QuickSort and HeapSort algorithms and measures:
 * - Number of key comparisons
 * - Number of record copies/swaps
 * - Total sorting time (machine time)
 * 
 * @author Débora Duarte, Fabrício Guidine, Walkíria Garcia
 * @version 1.0
 */
public class SortingExperiment {
    
    private static final int NUM_ALGORITHMS = 2;
    
    /**
     * Performs HeapSort sorting on the array of records.
     * After sorting, generates a PerformanceResult object and stores it.
     * 
     * @param records Array of Record objects where data is stored.
     * @param count Integer that represents the size of the Record array.
     * @param outputWriter OutputFileWriter object where sorted data ready for output is stored.
     */
    private static void performHeapSort(Record[] records, int count, OutputFileWriter outputWriter) {
        long time;
        String recordCount = Integer.toString(count);
        HeapSort heapSort = new HeapSort();
        
        long start = System.currentTimeMillis();
        heapSort.heapSort(records);
        long end = System.currentTimeMillis();
        time = end - start;
        
        PerformanceResult result = new PerformanceResult(
            "Heap Sort",
            recordCount,
            heapSort.getComparisons(),
            heapSort.getSwaps(),
            time
        );
        outputWriter.addResult(result);
    }
    
    /**
     * Performs QuickSort sorting on the array of records.
     * After sorting, generates a PerformanceResult object and stores it.
     * 
     * @param records Array of Record objects where data is stored.
     * @param count Integer that represents the size of the Record array.
     * @param outputWriter OutputFileWriter object where sorted data ready for output is stored.
     */
    private static void performQuickSort(Record[] records, int count, OutputFileWriter outputWriter) {
        long time;
        String recordCount = Integer.toString(count);
        QuickSort quickSort = new QuickSort();
        
        long start = System.currentTimeMillis();
        quickSort.quickSort(records, 0, count);
        long end = System.currentTimeMillis();
        time = end - start;
        
        PerformanceResult result = new PerformanceResult(
            "Quick Sort",
            recordCount,
            quickSort.getComparisons(),
            quickSort.getSwaps(),
            time
        );
        outputWriter.addResult(result);
    }
    
    /**
     * Runs the sorting experiment.
     * 
     * @param inputFile Name of the input file containing test sizes (e.g., "entrada.txt").
     * @param datasetFile Name of the dataset CSV file.
     */
    public static void run(String inputFile, String datasetFile) {
        try {
            Record[] records;
            Record[] recordsCopy;
            
            // Read input file
            int[] entries = FileReader.readInputFile(inputFile);
            System.out.println("Number of test cases: " + entries[0]);
            
            int entryCount = entries[0] + 1;
            int resultCount = entries[0] * NUM_ALGORITHMS;
            OutputFileWriter outputWriter = new OutputFileWriter(resultCount);
            
            // Process each test case
            for (int i = 1; i < entryCount; i++) {
                System.out.println("Processing test case: " + entries[i] + " records");
                
                records = new Record[entries[i]];
                FileReader.loadRandomRecords(records, entries[i], datasetFile);
                
                // Create a copy for QuickSort (HeapSort modifies the original)
                recordsCopy = new Record[entries[i]];
                System.arraycopy(records, 0, recordsCopy, 0, entries[i]);
                
                // Perform HeapSort
                performHeapSort(records, entries[i], outputWriter);
                
                // Perform QuickSort on the copy
                performQuickSort(recordsCopy, entries[i], outputWriter);
            }
            
            System.out.println("Results saved!");
            outputWriter.writeResults("saida.txt");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Main method for sorting experiment.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        run("src/main/resources/entrada.txt", "data/dataset_simp_sem_descricao.csv");
    }
}

