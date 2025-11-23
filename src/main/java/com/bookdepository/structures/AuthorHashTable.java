package com.bookdepository.structures;

import com.bookdepository.model.Author;

/**
 * Hash table for storing Author objects.
 * Uses open addressing with double hashing for collision resolution.
 * 
 * @author Fabrício Guidine, Débora Duarte, Walkíria Garcia
 * @version 1.0
 */
public class AuthorHashTable {
    
    private final int size;
    private final Author[] hash;
    
    /**
     * Constructor for the AuthorHashTable class.
     * 
     * @param size Integer that represents the size of the table.
     */
    public AuthorHashTable(int size) {
        this.size = size;
        this.hash = new Author[size];
        
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
     * Returns the array where the objects are stored.
     * 
     * @return an Array of Author objects.
     */
    public Author[] getHash() {
        return this.hash;
    }
    
    /**
     * Hash function that determines the position occupied by a key.
     * Uses double hashing for collision resolution.
     * 
     * @param code Integer that is the key to be stored/searched.
     * @param i Integer that is incremented by one in case of collision until
     *          an empty position is found.
     * @return an Integer that is the position where the key will be stored/searched.
     */
    private int hashFunction(int code, int i) {
        int key = code;
        long hk1 = key % size;
        long hk2 = 1 + (key % (size - 1));
        return (int) ((hk1 + i * hk2) % size);
    }
    
    /**
     * Inserts a new key to be stored.
     * 
     * @param author Author object that is the object to be inserted.
     */
    public void insert(Author author) {
        int i = 0;
        int position = hashFunction(author.getCode(), i);
        
        while (hash[position] != null && i < size) {
            i++;
            position = hashFunction(author.getCode(), i);
        }
        
        if (i < size) {
            hash[position] = author;
        }
    }
    
    /**
     * Searches for a key stored in the table.
     * 
     * @param code Integer that is the key to be searched.
     * @return the Author that is the found key. If the function does not find
     *         the searched object, the return will be null.
     */
    public Author search(int code) {
        int i = 0;
        int position = hashFunction(code, i);
        
        while (i < size) {
            if (hash[position] != null && hash[position].getCode() == code) {
                return hash[position];
            } else {
                i++;
                position = hashFunction(code, i);
            }
        }
        
        return null;
    }
}

