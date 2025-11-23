# Book Depository Data Structures Analysis

A comprehensive data structures and algorithms analysis project using the Book Depository dataset from Kaggle. This project implements and compares various sorting algorithms, hash tables, and balanced tree structures.

## Overview

This repository contains three main parts that analyze different aspects of data structures:

- **Part I**: Sorting algorithm analysis (QuickSort and HeapSort)
- **Part II**: Most frequent authors implementation using hash tables
- **Part III**: Balanced tree structures analysis (Red-Black Tree, B+ Trees)

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Python 3.7+ (for dataset download)
- [Kaggle API](https://www.kaggle.com/docs/api) credentials (for dataset access)

## Getting Started

### 1. Dataset Setup

The dataset is available on [Kaggle](https://www.kaggle.com/sp1thas/book-depository-dataset). Download it using the provided Python script:

```bash
# Install Python dependencies
pip install -r scripts/requirements.txt

# Download the dataset (requires Kaggle API credentials)
python scripts/download_dataset.py
```

**Note**: Configure your Kaggle API credentials. Place `kaggle.json` in `~/.kaggle/` or set `KAGGLE_USERNAME` and `KAGGLE_KEY` environment variables.

The script downloads the dataset to the `data/` directory:
- `authors.csv` - Author information
- `dataset_simp_sem_descricao.csv` - Book records

### 2. Project Structure

```
.
├── data/                        # Dataset files (downloaded from Kaggle)
├── docs/                        # Documentation
│   ├── requirements.pdf        # Project requirements (PDF)
│   └── latex/                  # LaTeX technical report
├── input/                       # Input files for experiments
│   └── entrada.txt             # Test sizes configuration
├── output/                      # Output files from experiments
│   ├── saida.txt               # Part I results
│   ├── saidaPart2.txt          # Part II results
│   ├── saidaInsercao.txt       # Part III insertion results
│   └── saidaBusca.txt          # Part III search results
├── scripts/                     # Utility scripts
│   ├── download_dataset.py     # Kaggle dataset downloader
│   └── requirements.txt        # Python dependencies
├── src/                         # Source code
│   └── main/
│       └── java/
│           └── com/bookdepository/
│               ├── model/           # Domain entities (Record, Author)
│               ├── algorithms/      # Algorithm implementations
│               │   └── sorting/     # Sorting algorithms
│               ├── structures/      # Data structure implementations
│               │   ├── hashtable/   # Hash tables
│               │   └── tree/        # Tree structures
│               ├── experiments/     # Benchmarking applications
│               ├── io/              # File I/O utilities
│               └── utils/           # Utility functions
└── tests/                       # Test results and analysis
```

### 3. Building and Running

#### Part I: Sorting Algorithms

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/SortingExperiment.java
java -cp ../../../build com.bookdepository.experiments.SortingExperiment
```

Output: `output/saida.txt`

#### Part II: Hash Tables (Most Frequent Authors)

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/HashTableExperiment.java
java -cp ../../../build com.bookdepository.experiments.HashTableExperiment
```

Output: `output/saidaPart2.txt`

#### Part III: Tree Structures

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/TreeExperiment.java
java -cp ../../../build com.bookdepository.experiments.TreeExperiment
```

Output: `output/saidaInsercao.txt` and `output/saidaBusca.txt`

## Input Format

Create `input/entrada.txt` with:

```
N
value1
value2
...
valueN
```

Where `N` is the number of test cases, and each value represents the number of records to process.

**Example:**
```
5
1000
5000
10000
50000
100000
```

## Documentation

- **Requirements**: See `docs/requirements.pdf`
- **Technical Report**: LaTeX source in `docs/latex/`

## Authors

- Débora Duarte
- Fabrício Guidine
- Walkíria Garcia

## License

This project is part of the coursework for DCC012 - Data Structures II at Universidade Federal de Juiz de Fora.
