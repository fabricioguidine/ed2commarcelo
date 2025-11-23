# Migration Plan - Complete Reorganization

## Overview
This document outlines the plan to reorganize all content from "parte 1", "parte 2", and "parte 3" folders into a clean, logical architecture.

## Target Architecture

```
src/main/java/com/bookdepository/
├── model/              # Domain entities (Record, Author) ✅ DONE
├── algorithms/         # Reusable algorithms
│   └── sorting/        # Sorting algorithms ✅ DONE
├── structures/         # Data structures
│   ├── hashtable/      # Hash tables ✅ DONE
│   └── tree/           # Tree structures
│       ├── redblack/   # Red-Black Tree ⚠️ NEEDS MIGRATION
│       └── bplustree/  # B+ Tree ⚠️ NEEDS MIGRATION
├── experiments/        # Benchmarking applications
│   ├── SortingExperiment.java ✅ DONE
│   ├── HashTableExperiment.java ✅ DONE
│   └── TreeExperiment.java ⚠️ NEEDS CREATION
├── io/                 # I/O utilities ✅ DONE
└── utils/              # Utility functions
```

## Migration Status

### ✅ Already Migrated
- **Part I (Sorting)**: QuickSort, HeapSort, SortingExperiment
- **Part II (Hash Tables)**: AuthorHashTable, RecordHashTable, HashTableExperiment
- **Models**: Record, Author
- **I/O Utilities**: FileReader, OutputFileWriter, Part2OutputWriter, PerformanceResult

### ⚠️ Needs Migration (Part III)
- **Red-Black Tree**:
  - `ArvoreVP.java` → `structures/tree/redblack/RedBlackTree.java`
  - `NoVP.java` → `structures/tree/redblack/RedBlackNode.java`

- **B+ Tree**:
  - `BPlusTree.java` → `structures/tree/bplustree/BPlusTree.java`
  - `No.java` → `structures/tree/bplustree/BPlusNode.java`
  - `NoInterno.java` → `structures/tree/bplustree/BPlusInternalNode.java`
  - `NoFolha.java` → `structures/tree/bplustree/BPlusLeafNode.java`
  - `DictionaryPair.java` → `structures/tree/bplustree/DictionaryPair.java`

- **Experiment**:
  - `Trabalho03.java` → `experiments/TreeExperiment.java`

## Migration Tasks

1. **Translate all classes to English**
   - Rename classes, methods, variables
   - Translate comments and documentation
   - Update package declarations

2. **Adapt to new model**
   - Replace `Registro` with `Record`
   - Use `com.bookdepository.model.Record`
   - Update method calls (e.g., `intGetId()` exists)

3. **Update I/O handling**
   - Use new `FileReader` utility
   - Use new `OutputFileWriter` utility
   - Update file paths to use `input/` and `output/` directories

4. **Remove old folders**
   - Delete `parte 1/`, `parte 2/`, `parte 3/` after migration
   - Already added to `.gitignore`

## Next Steps
1. Create Red-Black Tree classes
2. Create B+ Tree classes
3. Create TreeExperiment
4. Test all experiments
5. Remove old folders

