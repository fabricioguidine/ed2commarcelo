package com.bookdepository.structures;

import com.bookdepository.model.Record;

/**
 * Hash table for storing Record objects.
 * Uses open addressing with double hashing for collision resolution.
 * 
 * @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
 * @version 1.0
 */
public class RecordHashTable {
    
    private final int size;
    private final Record[] hash;
    
    /**
     * Constructor for the RecordHashTable class.
     * 
     * @param size Integer that represents the size of the table.
     */
    public RecordHashTable(int size) {
        this.size = size;
        this.hash = new Record[size];
        
        for (int j = 0; j < size; j++) {
            hash[j] = null;
        }
    }
    
    /**
     * Returns the size of the table.
     * 
     * @return size as an Integer.
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * Hash function that determines the position occupied by a key.
     * Uses double hashing for collision resolution.
     * 
     * @param record Record object that is the object to be stored/searched.
     * @param i Integer that is incremented by one in case of collision until
     *          an empty position is found.
     * @return an Integer that is the position where the key will be stored/searched.
     */
    private int hashFunction(Record record, int i) {
        try {
            long vkey = Long.parseLong(record.getId());
            int x = (int) (vkey / 1000);
            long hk1 = x % size;
            long hk2 = 1 + (x % (size - 1));
            return (int) ((hk1 + i * hk2) % size);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Inserts a new key to be stored.
     * 
     * @param record Record object that is the key to be inserted.
     */
    public void insert(Record record) {
        int i = 0;
        int position = hashFunction(record, i);
        
        while (hash[position] != null && i < size) {
            i++;
            position = hashFunction(record, i);
        }
        
        if (i < size) {
            hash[position] = record;
        }
    }
    
    /**
     * Searches for a key stored in the table.
     * 
     * @param record Record object that is the key to be searched.
     * @return the Record that is the found key. If the function does not find
     *         the searched object, the return will be null.
     */
    public Record search(Record record) {
        int i = 0;
        int position = hashFunction(record, i);
        
        while (i < size && hash[position] != null) {
            if (hash[position].getId().equals(record.getId())) {
                return hash[position];
            }
            i++;
            position = hashFunction(record, i);
        }
        
        return null;
    }
}

