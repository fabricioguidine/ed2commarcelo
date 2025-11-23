#!/bin/bash

# Test script for running JUnit tests

echo "Running JUnit tests for Performance Analysis for Data Structures and Algorithms..."

# Check if JUnit JARs exist
if [ ! -f "lib/junit-4.13.2.jar" ] || [ ! -f "lib/hamcrest-core-1.3.jar" ]; then
    echo "ERROR: JUnit JAR files not found in lib/ directory."
    echo "Please download them using the instructions in lib/README.md"
    exit 1
fi

# Create build directories
mkdir -p build/classes
mkdir -p build/test

# Compile main source files
echo "Compiling main source files..."
javac -d build/classes -sourcepath src/main/java \
    src/main/java/com/bookdepository/model/*.java \
    src/main/java/com/bookdepository/io/*.java \
    src/main/java/com/bookdepository/algorithms/sorting/*.java \
    src/main/java/com/bookdepository/structures/hashtable/*.java \
    src/main/java/com/bookdepository/structures/tree/redblack/*.java \
    src/main/java/com/bookdepository/structures/tree/bplustree/*.java \
    src/main/java/com/bookdepository/experiments/*.java

if [ $? -ne 0 ]; then
    echo "Compilation of main source files failed!"
    exit 1
fi

# Compile test files
echo "Compiling test files..."
javac -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:build/classes" \
    -d build/test \
    src/test/java/com/bookdepository/model/*.java \
    src/test/java/com/bookdepository/algorithms/sorting/*.java \
    src/test/java/com/bookdepository/structures/hashtable/*.java \
    src/test/java/com/bookdepository/structures/tree/redblack/*.java \
    src/test/java/com/bookdepository/structures/tree/bplustree/*.java \
    src/test/java/com/bookdepository/io/*.java

if [ $? -ne 0 ]; then
    echo "Compilation of test files failed!"
    exit 1
fi

# Create test classpath
TEST_CP="lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:build/classes:build/test"

echo ""
echo "Running tests..."
echo ""

# Run all test classes
for test_class in com.bookdepository.model.RecordTest \
                 com.bookdepository.model.AuthorTest \
                 com.bookdepository.algorithms.sorting.QuickSortTest \
                 com.bookdepository.algorithms.sorting.HeapSortTest \
                 com.bookdepository.structures.hashtable.RecordHashTableTest \
                 com.bookdepository.structures.hashtable.AuthorHashTableTest \
                 com.bookdepository.structures.tree.redblack.RedBlackTreeTest \
                 com.bookdepository.structures.tree.bplustree.BPlusTreeTest \
                 com.bookdepository.io.PerformanceResultTest \
                 com.bookdepository.io.FileReaderTest \
                 com.bookdepository.io.OutputFileWriterTest \
                 com.bookdepository.io.Part2OutputWriterTest; do
    echo "Running $test_class..."
    java -cp "$TEST_CP" org.junit.runner.JUnitCore "$test_class"
    echo ""
done

echo "Test execution complete!"

