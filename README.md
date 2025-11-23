# Book Depository Data Structures Analysis

A comprehensive data structures and algorithms analysis project using the Book Depository dataset from Kaggle. This project implements and compares various sorting algorithms, hash tables, and balanced tree structures.

## Overview

This repository contains three main parts that analyze different aspects of data structures:

- **Part I**: Sorting algorithm analysis (QuickSort and HeapSort)
- **Part II**: Most frequent authors implementation using hash tables
- **Part III**: Search in balanced and self-adjusting structures (Red-Black Tree, B+ Trees)

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Python 3.7+ (for dataset download)
- [Kaggle API](https://www.kaggle.com/docs/api) credentials (for dataset access)

## Getting Started

### 1. Dataset Setup

The dataset is available on [Kaggle](https://www.kaggle.com/sp1thas/book-depository-dataset). You can download it using the provided Python script:

```bash
# Install Python dependencies
pip install -r scripts/requirements.txt

# Download the dataset (requires Kaggle API credentials)
python scripts/download_dataset.py
```

**Note**: You need to set up your Kaggle API credentials. Place your `kaggle.json` file in `~/.kaggle/` directory or set the `KAGGLE_USERNAME` and `KAGGLE_KEY` environment variables.

The script will download the dataset files to the `data/` directory:
- `authors.csv` - Author information
- `dataset_simp_sem_descricao.csv` - Book records

### 2. Project Structure

```
.
├── data/                    # Dataset files (downloaded from Kaggle)
├── scripts/                 # Python scripts for data management
│   ├── download_dataset.py
│   └── requirements.txt
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── com/bookdepository/
│   │       │   ├── model/           # Data models (Record, Author)
│   │       │   ├── algorithms/      # Sorting algorithms
│   │       │   ├── structures/      # Data structures (Hash tables, Trees)
│   │       │   ├── io/              # File I/O utilities
│   │       │   └── part1/           # Part I implementation
│   │       │   └── part2/           # Part II implementation
│   │       │   └── part3/           # Part III implementation
│   │       └── resources/           # Configuration and input files
└── README.md
```

### 3. Building the Project

This project uses standard Java build tools. Each part can be compiled and run independently.

## Part I: Sorting Algorithm Analysis

### Description

This part analyzes the performance of sorting algorithms on book records. It implements QuickSort and HeapSort algorithms and measures:
- Number of key comparisons
- Number of record copies/swaps
- Total sorting time (machine time)

### Algorithms Implemented

- **QuickSort**: Uses median-of-three pivot selection
- **HeapSort**: Builds a max-heap and sorts by repeatedly extracting the maximum

### Input Format

Create an `entrada.txt` file in the project root with the following format:
```
N
value1
value2
...
valueN
```

Where `N` is the number of test cases, and each value represents the number of records to sort.

**Example:**
```
5
1000
5000
10000
50000
100000
```

### Running Part I

```bash
cd src/main/java/com/bookdepository/part1
javac -d ../../../../../../build *.java
java com.bookdepository.part1.Part1
```

The output will be written to `saida.txt` in the project root.

## Part II: Most Frequent Authors

### Description

Implements a program that reads N random and distinct books and counts how many times the same author appears within those N books using hash tables. The program prints the most frequent authors.

### Data Structures Used

- **Hash Table for Records**: Stores book records using open addressing with double hashing
- **Hash Table for Authors**: Stores author information and their frequencies

### Running Part II

```bash
cd src/main/java/com/bookdepository/part2
javac -d ../../../../../../build *.java
java com.bookdepository.part2.Part2
```

The program will prompt for the number `N` of top authors to display. Output will be written to `saidaPart2.txt`.

## Part III: Balanced and Self-Adjusting Structures

### Description

Evaluates the performance of balanced tree structures when inserting books using the book ID as the key. Also analyzes the performance of these structures when searching for books.

### Structures Analyzed

- **Red-Black Tree**: Self-balancing binary search tree
- **B+ Tree (d=2)**: B+ tree with minimum degree 2
- **B+ Tree (d=20)**: B+ tree with minimum degree 20

### Performance Metrics

For each structure and each value of N from the input file:
- Insertion statistics (comparisons, swaps, time)
- Search statistics (comparisons, swaps, time)

Output files:
- `saidaInsercao.txt`: Insertion performance statistics
- `saidaBusca.txt`: Search performance statistics

### Running Part III

```bash
cd src/main/java/com/bookdepository/part3
javac -d ../../../../../../build *.java
java com.bookdepository.part3.Part3
```

## Project Details

### Dataset Schema

Each book record contains:
- `authors` [list]: List of author IDs
- `bestsellers_rank` [int]: Bestseller ranking
- `categories` [list]: Book categories
- `edition` [str]: Edition information
- `id` [int]: Unique book identifier
- `isbn10` [str]: ISBN-10
- `isbn13` [str]: ISBN-13
- `rating_avg` [float]: Average rating (0-5)
- `rating_count` [int]: Number of ratings
- `title` [str]: Book title

### Architecture

The project follows a modular architecture with clear separation of concerns:

- **Model Layer**: Data models (`Record`, `Author`)
- **Algorithm Layer**: Sorting and search algorithms
- **Structure Layer**: Data structures (hash tables, trees)
- **IO Layer**: File reading and writing utilities
- **Application Layer**: Main programs for each part

## Contributing

This project was created as part of a Data Structures course (DCC012) at the Computer Science Department of UFJF.

## License

This project is for educational purposes.

## Authors

- Débora Duarte
- Fabrício Guidine
- Walkíria Garcia

## Acknowledgments

- Dataset provided by [Book Depository on Kaggle](https://www.kaggle.com/sp1thas/book-depository-dataset)
- Department of Computer Science, UFJF
