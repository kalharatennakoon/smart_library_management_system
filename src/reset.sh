#!/bin/bash

# Get the directory where the script is located
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_ROOT="$SCRIPT_DIR/.."
SRC_DIR="$SCRIPT_DIR"

# If run from project root, src/ must exist
if [ -d "$SCRIPT_DIR/src" ]; then
  cd "$SCRIPT_DIR/src" || exit 1
elif [ -d "$SRC_DIR" ] && [ "$(basename "$SRC_DIR")" = "src" ]; then
  cd "$SRC_DIR" || exit 1
else
  echo "Error: This script must be run from the project root or src directory."
  exit 1
fi

# Remove all .class files under src/ (recursive)
find . -type f -name "*.class" -delete

echo "Build reset: All .class files have been deleted."



