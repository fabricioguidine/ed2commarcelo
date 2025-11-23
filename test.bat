@echo off
REM Test script for running JUnit tests (Windows)

echo Running JUnit tests for Performance Analysis for Data Structures and Algorithms...

REM Check if JUnit JARs exist
if not exist "lib\junit-4.13.2.jar" (
    echo ERROR: JUnit JAR file not found in lib\ directory.
    echo Please download it using the instructions in lib\README.md
    exit /b 1
)

if not exist "lib\hamcrest-core-1.3.jar" (
    echo ERROR: Hamcrest JAR file not found in lib\ directory.
    echo Please download it using the instructions in lib\README.md
    exit /b 1
)

REM Create build directories
mkdir build\classes 2>nul
mkdir build\test 2>nul

REM Compile main source files
echo Compiling main source files...
javac -d build\classes -sourcepath src\main\java ^
    src\main\java\com\bookdepository\model\*.java ^
    src\main\java\com\bookdepository\io\*.java ^
    src\main\java\com\bookdepository\algorithms\sorting\*.java ^
    src\main\java\com\bookdepository\structures\hashtable\*.java ^
    src\main\java\com\bookdepository\structures\tree\redblack\*.java ^
    src\main\java\com\bookdepository\structures\tree\bplustree\*.java ^
    src\main\java\com\bookdepository\experiments\*.java

if errorlevel 1 (
    echo Compilation of main source files failed!
    exit /b 1
)

REM Compile test files
echo Compiling test files...
javac -cp "lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar;build\classes" ^
    -d build\test ^
    src\test\java\com\bookdepository\model\*.java ^
    src\test\java\com\bookdepository\algorithms\sorting\*.java ^
    src\test\java\com\bookdepository\structures\hashtable\*.java ^
    src\test\java\com\bookdepository\structures\tree\redblack\*.java ^
    src\test\java\com\bookdepository\structures\tree\bplustree\*.java ^
    src\test\java\com\bookdepository\io\*.java

if errorlevel 1 (
    echo Compilation of test files failed!
    exit /b 1
)

REM Create test classpath
set TEST_CP=lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar;build\classes;build\test

echo.
echo Running tests...
echo.

REM Run all test classes
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.model.RecordTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.model.AuthorTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.algorithms.sorting.QuickSortTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.algorithms.sorting.HeapSortTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.structures.hashtable.RecordHashTableTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.structures.hashtable.AuthorHashTableTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.structures.tree.redblack.RedBlackTreeTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.structures.tree.bplustree.BPlusTreeTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.io.PerformanceResultTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.io.FileReaderTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.io.OutputFileWriterTest
java -cp "%TEST_CP%" org.junit.runner.JUnitCore com.bookdepository.io.Part2OutputWriterTest

echo.
echo Test execution complete!

