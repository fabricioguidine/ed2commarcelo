package com.bookdepository.algorithms.sorting;

import com.bookdepository.model.Record;

/**
 * Implementation of the QuickSort algorithm for sorting records.
 * 
 * @author Débora Duarte, Fabrício Guidine
 * @version 1.0
 */
public class QuickSort {
    
    private int comparisons;
    private int swaps;
    
    /**
     * Constructor for the QuickSort class.
     */
    public QuickSort() {
        this.comparisons = 0;
        this.swaps = 0;
    }
    
    /**
     * Function responsible for sorting the array using the QuickSort algorithm.
     * Uses median-of-three pivot selection strategy.
     * 
     * @param array Array of Record objects to be sorted.
     * @param start Starting position of the array.
     * @param end Ending position of the array (exclusive).
     */
    public void quickSort(Record[] array, int start, int end) {
        int i, j, median;
        i = start;
        j = end - 1;
        median = (start + end + ((start + end) / 2)) / 3; // median is pivot, chosen by median of 3
        
        while (i <= j) {
            // Traverse the array from left to right while array[i] < pivot
            comparisons++;
            while (i < end && array[i].getTitle().compareTo(array[median].getTitle()) < 0) {
                i++;
                comparisons++;
            }
            
            // Then, traverse the array from right to left while array[j] > pivot
            comparisons++;
            while (j > start && array[j].getTitle().compareTo(array[median].getTitle()) > 0) {
                j--;
                comparisons++;
            }
            
            // Finally, swap array[i] with array[j]. This process continues until pointers i and j cross.
            if (i <= j) {
                swaps++;
                Record temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        
        if (j > start) {
            quickSort(array, start, j + 1);
        }
        if (i < end) {
            quickSort(array, i, end);
        }
    }
    
    /**
     * Returns the number of comparisons performed by QuickSort.
     * 
     * @return comparisons as an Integer.
     */
    public int getComparisons() {
        return comparisons;
    }
    
    /**
     * Returns the number of swaps performed by QuickSort.
     * 
     * @return swaps as an Integer.
     */
    public int getSwaps() {
        return swaps;
    }
    
    /**
     * Resets the comparison and swap counters.
     */
    public void reset() {
        this.comparisons = 0;
        this.swaps = 0;
    }
}

