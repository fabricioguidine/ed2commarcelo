#!/bin/bash
# Bash script to push to GitHub using a token
# Usage: ./push_with_token.sh <your_github_token>

if [ -z "$1" ]; then
    echo "Usage: $0 <your_github_token>"
    exit 1
fi

TOKEN=$1
REMOTE_URL="https://${TOKEN}@github.com/fabricioguidine/performance-analysis-4-ds-and-algo.git"

echo "Setting remote URL with token..."
git remote set-url origin "$REMOTE_URL"

echo "Pushing to origin/master..."
git push origin master

echo "Push complete!"

