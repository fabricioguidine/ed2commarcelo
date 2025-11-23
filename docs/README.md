# Documentation

This directory contains all project documentation.

## Contents

- `requirements.pdf` - Project specification document (original PDF in Portuguese)
- `latex/` - LaTeX source files for the technical report

## LaTeX Report

The technical report is written in LaTeX using the abnTeX2 template, following ABNT standards.

### Compilation

See `latex/README.md` for compilation instructions.

### Report Structure

The report analyzes:

1. **Sorting Algorithms** (QuickSort, HeapSort)
   - Performance metrics: comparisons, swaps, execution time
   - Results from `../tests/sorting/`

2. **Hash Tables** (Author and Record hash tables)
   - Frequency analysis of authors
   - Results from `../tests/hashtable/`

3. **Balanced Trees** (Red-Black Tree, B+ Trees)
   - Insertion and search performance
   - Results from `../tests/trees/`

## Adding Results

Experimental results should be placed in the `tests/` directory and referenced in the LaTeX report.

