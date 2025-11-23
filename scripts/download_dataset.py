#!/usr/bin/env python3
"""
Script to download the Book Depository dataset from Kaggle.
This script uses the Kaggle API to download the dataset files.

Requirements:
    - kaggle package: pip install kaggle
    - Kaggle API credentials: Place kaggle.json in ~/.kaggle/ or set KAGGLE_USERNAME and KAGGLE_KEY environment variables

Usage:
    python scripts/download_dataset.py
"""

import os
import sys
from pathlib import Path

try:
    from kaggle.api.kaggle_api_extended import KaggleApi
except ImportError:
    print("Error: kaggle package not found. Install it with: pip install kaggle")
    sys.exit(1)


def download_dataset():
    """Download the Book Depository dataset from Kaggle."""
    
    # Dataset identifier from Kaggle
    dataset_name = "sp1thas/book-depository-dataset"
    
    # Output directory
    data_dir = Path("data")
    data_dir.mkdir(exist_ok=True)
    
    print(f"Downloading dataset: {dataset_name}")
    print(f"Output directory: {data_dir.absolute()}")
    
    try:
        # Initialize Kaggle API
        api = KaggleApi()
        api.authenticate()
        
        # Download dataset files
        print("\nDownloading dataset files...")
        api.dataset_download_files(
            dataset_name,
            path=str(data_dir),
            unzip=True
        )
        
        print("\nDataset downloaded successfully!")
        print(f"Files are in: {data_dir.absolute()}")
        
        # List downloaded files
        print("\nDownloaded files:")
        for file in sorted(data_dir.glob("*")):
            if file.is_file():
                print(f"  - {file.name} ({file.stat().st_size / (1024*1024):.2f} MB)")
        
        return True
        
    except Exception as e:
        print(f"\nError downloading dataset: {str(e)}")
        print("\nTroubleshooting:")
        print("1. Make sure you have Kaggle API credentials set up")
        print("2. Place kaggle.json in ~/.kaggle/ directory")
        print("3. Or set KAGGLE_USERNAME and KAGGLE_KEY environment variables")
        print("4. Accept the dataset rules on Kaggle website")
        return False


if __name__ == "__main__":
    success = download_dataset()
    sys.exit(0 if success else 1)

