package com.bookdepository.algorithms.sorting;

import com.bookdepository.model.Record;

/**
 * Implementation of the HeapSort algorithm for sorting records.
 * 
 * @author Débora Duarte, Walkíria Garcia
 * @version 1.0
 */
public class HeapSort {
    
    private int comparisons;
    private int swaps;
    
    /**
     * Constructor for the HeapSort class.
     */
    public HeapSort() {
        comparisons = 0;
        swaps = 0;
    }
    
    /**
     * Builds the heap.
     * Swaps the item at position 1 of the Array with the item at the last position.
     * Uses the MaxHeapify procedure to rebuild the heap for items A[1], A[2],..., A[n-1], where n = array size.
     * 
     * @param array Array of Record objects to be sorted.
     */
    public void heapSort(Record[] array) {
        int n = array.length;
        
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(array, n, i);
        }
        
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            swaps++;
            Record temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            
            // Call maxHeapify on the reduced heap
            maxHeapify(array, i, 0);
        }
    }
    
    /**
     * Verifies that the max heap property is not violated at a given node of the tree.
     * 
     * @param array Array of Record objects to be sorted.
     * @param n Integer that represents the size of the array.
     * @param i Integer that represents the position of the parent node.
     */
    private void maxHeapify(Record[] array, int n, int i) {
        int parent = i; // Initialize parent as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2
        
        // If left child is larger than parent
        comparisons++;
        if (left < n && array[left].getTitle().compareTo(array[parent].getTitle()) > 0) {
            parent = left;
        }
        
        // If right child is larger than parent
        comparisons++;
        if (right < n && array[right].getTitle().compareTo(array[parent].getTitle()) > 0) {
            parent = right;
        }
        
        // If parent is not root
        if (parent != i) {
            swaps++;
            Record swap = array[i];
            array[i] = array[parent];
            array[parent] = swap;
            
            // Recursively heapify the affected sub-tree
            maxHeapify(array, n, parent);
        }
    }
    
    /**
     * Sorts an array of Author objects by frequency using HeapSort.
     * 
     * @param array Array of Author objects to be sorted.
     */
    public void sort(com.bookdepository.model.Author[] array) {
        int n = array.length;
        
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(array, n, i);
        }
        
        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            com.bookdepository.model.Author temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            
            maxHeapify(array, i, 0);
        }
    }
    
    /**
     * Max heapify for Author array sorted by frequency.
     */
    private void maxHeapify(com.bookdepository.model.Author[] array, int n, int i) {
        int parent = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && array[left] != null && (array[parent] == null || 
            array[left].getFrequency() > array[parent].getFrequency())) {
            parent = left;
        }
        
        if (right < n && array[right] != null && (array[parent] == null || 
            array[right].getFrequency() > array[parent].getFrequency())) {
            parent = right;
        }
        
        if (parent != i) {
            com.bookdepository.model.Author swap = array[i];
            array[i] = array[parent];
            array[parent] = swap;
            
            maxHeapify(array, n, parent);
        }
    }
    
    /**
     * Returns the number of comparisons performed by HeapSort.
     * 
     * @return comparisons as an Integer.
     */
    public int getComparisons() {
        return comparisons;
    }
    
    /**
     * Returns the number of swaps performed by HeapSort.
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

