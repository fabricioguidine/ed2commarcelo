# Performance Analysis for Data Structures and Algorithms

> A comprehensive performance analysis of fundamental data structures and algorithms using the Book Depository dataset

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![Python](https://img.shields.io/badge/Python-3.7%2B-blue.svg)](https://www.python.org/)
[![License](https://img.shields.io/badge/License-Academic-blue.svg)](LICENSE)

This project provides a comprehensive performance analysis of fundamental data structures and algorithms using the [Book Depository dataset](https://www.kaggle.com/sp1thas/book-depository-dataset) from Kaggle. It implements and empirically evaluates sorting algorithms (QuickSort, HeapSort), hash tables, and balanced tree structures (Red-Black Tree, B+ Trees) with real-world book and author data.

## 🎯 Overview

This repository contains three main experimental parts that analyze different aspects of data structures:

- **Part I**: Sorting algorithm performance analysis (QuickSort and HeapSort)
- **Part II**: Most frequent authors identification using hash tables
- **Part III**: Balanced tree structures performance evaluation (Red-Black Tree, B+ Trees)

## 📋 Table of Contents

- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
  - [Dataset Setup](#dataset-setup)
  - [Project Structure](#project-structure)
  - [Building the Project](#building-the-project)
- [Usage](#-usage)
- [JAR File](#-jar-file)
  - [Part I: Sorting Algorithms](#part-i-sorting-algorithms)
  - [Part II: Hash Tables](#part-ii-hash-tables)
  - [Part III: Tree Structures](#part-iii-tree-structures)
- [Project Structure](#-project-structure)
- [Documentation](#-documentation)
- [Contributing](#-contributing)
- [Authors](#-authors)
- [License](#-license)

## 🔧 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** 8 or higher
  - Verify installation: `java -version`
- **Maven** 3.6 or higher (for dependency management and building)
  - Verify installation: `mvn -version`
  - Download from: [Maven Download](https://maven.apache.org/download.cgi)
- **Python** 3.7 or higher
  - Verify installation: `python --version`
- **Kaggle API** credentials for dataset access
  - Sign up at [Kaggle](https://www.kaggle.com/)
  - Follow [API setup instructions](https://www.kaggle.com/docs/api)

## 🚀 Getting Started

### Dataset Setup

The dataset is available on [Kaggle](https://www.kaggle.com/sp1thas/book-depository-dataset). Download it using the provided Python script:

**Step 1:** Install Python dependencies

```bash
pip install -r scripts/requirements.txt
```

**Step 2:** Configure Kaggle API credentials

Place your `kaggle.json` file in:
- **Windows**: `C:\Users\<username>\.kaggle\kaggle.json`
- **Linux/Mac**: `~/.kaggle/kaggle.json`

Or set environment variables:
```bash
export KAGGLE_USERNAME=your_username
export KAGGLE_KEY=your_api_key
```

**Step 3:** Download the dataset

```bash
python scripts/download_dataset.py
```

The script will download the dataset files to the `data/` directory:
- `authors.csv` - Author information
- `dataset_simp_sem_descricao.csv` - Book records

### Project Structure

```
.
├── data/                        # Dataset files (downloaded from Kaggle)
│   ├── authors.csv
│   └── dataset_simp_sem_descricao.csv
├── docs/                        # Documentation
│   ├── requirements.pdf        # Project requirements (PDF)
│   └── latex/                  # LaTeX technical report
│       ├── relatorio.tex       # Main LaTeX document
│       ├── referencias.bib     # Bibliography
│       └── Makefile            # Build automation
├── input/                       # Input files for experiments
│   ├── entrada.txt             # Test sizes configuration
│   └── README.md
├── output/                      # Output files from experiments
│   ├── saida.txt               # Part I results
│   ├── saidaPart2.txt          # Part II results
│   ├── saidaInsercao.txt       # Part III insertion results
│   ├── saidaBusca.txt          # Part III search results
│   └── README.md
├── jar-build/                   # JAR build scripts and tools
│   ├── Makefile                # Make-based build
│   ├── build.sh                # Bash build script
│   ├── build.bat               # Windows build script
│   └── README.md               # Build instructions
├── scripts/                     # Utility scripts
│   ├── download_dataset.py     # Kaggle dataset downloader
│   └── requirements.txt        # Python dependencies
├── src/                         # Source code
│   └── main/
│       └── java/
│           └── com/bookdepository/
│               ├── model/           # Domain entities
│               │   ├── Record.java
│               │   └── Author.java
│               ├── algorithms/      # Algorithm implementations
│               │   └── sorting/
│               │       ├── QuickSort.java
│               │       └── HeapSort.java
│               ├── structures/      # Data structure implementations
│               │   ├── hashtable/
│               │   │   ├── RecordHashTable.java
│               │   │   └── AuthorHashTable.java
│               │   └── tree/
│               │       ├── redblack/
│               │       └── bplustree/
│               ├── experiments/     # Benchmarking applications
│               │   ├── SortingExperiment.java
│               │   ├── HashTableExperiment.java
│               │   └── TreeExperiment.java
│               ├── io/              # File I/O utilities
│               │   ├── FileReader.java
│               │   ├── OutputFileWriter.java
│               │   ├── Part2OutputWriter.java
│               │   └── PerformanceResult.java
│               └── utils/           # Utility functions
├── tests/                       # Test results and test scripts
│   ├── scripts/                # JUnit test execution scripts
│   │   ├── test.sh             # Bash test script
│   │   ├── test.bat            # Windows test script
│   │   └── README.md           # Test script instructions
│   ├── sorting/                # Sorting algorithm test results
│   ├── hashtable/              # Hash table test results
│   └── trees/                  # Tree structure test results
├── .gitignore
├── README.md
└── LICENSE
```

### Building the Project

This project uses **Maven** for dependency management and build automation.

#### Prerequisites

- **Java Development Kit (JDK)** 8 or higher
- **Maven** 3.6 or higher
  - Verify installation: `mvn -version`
  - Download from: https://maven.apache.org/download.cgi

#### Building with Maven

**Build and run tests:**
```bash
mvn clean package
```

**Build without tests:**
```bash
mvn clean package -DskipTests
```

**Run tests only:**
```bash
mvn test
```

**Output:** 
- Main JAR: `target/bookdepository-ds-analysis.jar`
- JAR with dependencies: `target/bookdepository-ds-analysis-jar-with-dependencies.jar`

For manual build instructions (without Maven), see `jar-build/README.md`.

#### Running Experiments from JAR

After building with Maven, run experiments:

**Using JAR with dependencies (recommended):**
```bash
# Part I: Sorting Algorithms
java -jar target/bookdepository-ds-analysis-jar-with-dependencies.jar

# Part II: Hash Tables
java -cp target/bookdepository-ds-analysis-jar-with-dependencies.jar com.bookdepository.experiments.HashTableExperiment

# Part III: Tree Structures
java -cp target/bookdepository-ds-analysis-jar-with-dependencies.jar com.bookdepository.experiments.TreeExperiment
```

**Using main JAR:**
```bash
# Part I: Sorting Algorithms
java -cp target/bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment

# Part II: Hash Tables
java -cp target/bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment

# Part III: Tree Structures
java -cp target/bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
```

## 🧪 Testing

This project includes a comprehensive JUnit test suite to validate all implementations.

### Running Tests

**Using Maven (recommended):**
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=RecordTest

# Run tests with verbose output
mvn test -X
```

**Using test scripts (alternative):**
```bash
# Linux/Mac/Git Bash
cd tests/scripts
chmod +x test.sh
./test.sh

# Windows
cd tests\scripts
test.bat
```

**Note:** Maven automatically downloads JUnit dependencies. The manual `lib/` directory approach is no longer required when using Maven.

### Test Coverage

The test suite covers:
- **Model Classes**: `Record` and `Author` validation
- **Sorting Algorithms**: QuickSort and HeapSort correctness tests
- **Hash Tables**: `RecordHashTable` and `AuthorHashTable` functionality
- **Tree Structures**: Red-Black Tree and B+ Tree operations
- **I/O Utilities**: File reading and writing validation

### Test Requirements

**With Maven:** Dependencies are automatically managed. Simply run `mvn test`.

**Manual Setup (without Maven):**
- Download JUnit JAR files to `lib/` directory
- See `lib/README.md` for detailed instructions
- Use test scripts in `tests/scripts/`

All test classes are located in `src/test/java/com/bookdepository/`. See `tests/scripts/README.md` for more information.

## 💻 Usage

### Part I: Sorting Algorithms

Analyzes the performance of QuickSort and HeapSort algorithms on book records.

**Metrics measured:**
- Number of key comparisons
- Number of record copies/swaps
- Total execution time (milliseconds)

**Run the experiment:**

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/SortingExperiment.java
java -cp ../../../build com.bookdepository.experiments.SortingExperiment
```

**Output:** Results are written to `output/saida.txt`

### Part II: Hash Tables

Implements hash tables to identify the most frequent authors in a set of books.

**Data structures used:**
- **RecordHashTable**: Hash table for records using open addressing with double hashing
- **AuthorHashTable**: Hash table for authors with frequency tracking

**Run the experiment:**

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/HashTableExperiment.java
java -cp ../../../build com.bookdepository.experiments.HashTableExperiment
```

The program will prompt for the number `N` of top authors to display.

**Output:** Results are written to `output/saidaPart2.txt`

### Part III: Tree Structures

Evaluates the performance of balanced tree structures for insertion and search operations.

**Structures analyzed:**
- **Red-Black Tree**: Self-balancing binary search tree
- **B+ Tree (d=2)**: B+ tree with minimum degree 2
- **B+ Tree (d=20)**: B+ tree with minimum degree 20

**Performance metrics:**
- Insertion statistics (comparisons, swaps, time)
- Search statistics (comparisons, swaps, time)

**Run the experiment:**

```bash
cd src/main/java
javac -d ../../../build com/bookdepository/experiments/TreeExperiment.java
java -cp ../../../build com.bookdepository.experiments.TreeExperiment
```

**Output:** 
- Insertion results: `output/saidaInsercao.txt`
- Search results: `output/saidaBusca.txt`

### Input Format

Create `input/entrada.txt` with the following format:

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

## 📁 Project Structure

### Package Organization

```
com.bookdepository/
├── model/              # Domain entities
├── algorithms/         # Reusable algorithms
│   └── sorting/        # Sorting algorithms
├── structures/         # Data structure implementations
│   ├── hashtable/      # Hash tables
│   └── tree/           # Tree structures
├── experiments/        # Benchmarking applications
├── io/                 # File I/O utilities
└── utils/              # Utility functions
```

### Key Components

- **Model Layer**: Domain entities (`Record`, `Author`)
- **Algorithms**: Sorting algorithms with performance tracking
- **Data Structures**: Hash tables and balanced trees
- **Experiments**: Benchmarking applications for each part
- **I/O Utilities**: File reading and output writing utilities

## 📚 Documentation

### Requirements

The complete project requirements are available in:
- **PDF**: `docs/requirements.pdf`

### Technical Report

A comprehensive technical report in LaTeX (using abnTeX2) analyzing all experimental results is located in `docs/latex/`:

- **Main document**: `docs/latex/relatorio.tex`
- **Bibliography**: `docs/latex/referencias.bib`
- **Compiled PDF**: `docs/latex/relatorio.pdf` (needs to be compiled)
- **Build instructions**: See `docs/latex/README.md` or `docs/latex/COMPILE_PDF.md`

The report analyzes results from the `tests/` directory, including:
- Sorting algorithm performance (`tests/sorting/`)
- Hash table experiments (`tests/hashtable/`)
- Tree structure benchmarks (`tests/trees/`)

**To compile the PDF report:**

```bash
cd docs/latex
make pdf
```

Or use Overleaf (online LaTeX editor): https://www.overleaf.com/

**Note:** The compiled PDF (`relatorio.pdf`) should be committed to the repository after compilation.

## 🤝 Contributing

This is an academic project for DCC012 - Data Structures II at Universidade Federal de Juiz de Fora. Contributions are welcome for bug fixes and improvements.

## 👥 Authors

- **Débora Duarte**
- **Fabrício Guidine**
- **Walkíria Garcia**

## 📄 License

This project is part of the coursework for **DCC012 - Data Structures II** at **Universidade Federal de Juiz de Fora**.

---

## 📊 Dataset Schema

Each book record contains the following fields:

| Field | Type | Description |
|-------|------|-------------|
| `authors` | `List<String>` | List of author IDs |
| `bestsellers_rank` | `int` | Bestseller ranking |
| `categories` | `List<String>` | Book categories |
| `edition` | `String` | Edition information |
| `id` | `String` | Unique book identifier |
| `isbn10` | `String` | ISBN-10 |
| `isbn13` | `String` | ISBN-13 |
| `rating_avg` | `float` | Average rating (0-5) |
| `rating_count` | `int` | Number of ratings |
| `title` | `String` | Book title |

## 🔗 Related Links

- [Kaggle Dataset](https://www.kaggle.com/sp1thas/book-depository-dataset)
- [Kaggle API Documentation](https://www.kaggle.com/docs/api)
- [Universidade Federal de Juiz de Fora](https://www.ufjf.br/)

---

**Note:** This project is for academic purposes as part of the Data Structures II course at UFJF.

## 📦 JAR File

A pre-built JAR file is available in `dist/bookdepository-ds-analysis.jar` for convenience.

**To rebuild the JAR file:**
1. Ensure Java JDK 8+ is installed
2. Run the build script:
   - **Linux/Mac/Git Bash**: `make` or `./build.sh`
   - **Windows**: `build.bat`
3. The JAR will be created in `dist/bookdepository-ds-analysis.jar`
4. After building, clean up build artifacts: `make clean` or delete the `build/` directory

**Running from JAR:**
```bash
# Part I: Sorting Algorithms
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.SortingExperiment

# Part II: Hash Tables
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.HashTableExperiment

# Part III: Tree Structures
java -cp dist/bookdepository-ds-analysis.jar com.bookdepository.experiments.TreeExperiment
```
