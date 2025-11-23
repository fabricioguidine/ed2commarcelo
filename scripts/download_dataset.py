"""
Kaggle Dataset Downloader
Downloads the Book Depository dataset from Kaggle.
"""

import os
import kaggle

# Ensure the Kaggle API is configured
# You need to have kaggle.json in ~/.kaggle/ or set KAGGLE_USERNAME and KAGGLE_KEY env vars

DATA_DIR = "data"
os.makedirs(DATA_DIR, exist_ok=True)

print("Downloading Kaggle dataset 'sp1thas/book-depository-dataset'...")
try:
    kaggle.api.dataset_download_files('sp1thas/book-depository-dataset', path=DATA_DIR, unzip=True)
    print(f"Dataset downloaded and unzipped to '{DATA_DIR}/'")
except Exception as e:
    print(f"Error downloading dataset: {e}")
    print("Please ensure your Kaggle API credentials are correctly set up.")

# Verify downloaded files
expected_files = ['authors.csv', 'dataset_simp_sem_descricao.csv']
for f in expected_files:
    file_path = os.path.join(DATA_DIR, f)
    if not os.path.exists(file_path):
        print(f"Warning: Expected file '{f}' not found in '{DATA_DIR}/'")
    else:
        print(f"Found: {file_path}")
