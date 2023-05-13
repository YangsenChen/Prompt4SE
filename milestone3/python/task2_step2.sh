#!/bin/bash

# Define the parent directory
parent_dir="/mnt/c/Users/Yangsen/Desktop/cs598/milestone3/python/generated_code"

# Loop through all directories in the parent directory
for dir in "$parent_dir"/*; do
    if [ -d "$dir" ]; then
        # Check if the directory is of the format code_xx
        if [[ $(basename "$dir") == code_* ]]; then
            # Navigate into the directory
            cd "$dir"

            # Check if the report folder does not exist
            if [ ! -d "report" ]; then
                # Execute the mut.py command
                mut.py -t "$(basename "$dir")" -u "test_$(basename "$dir")" --report-html ./report
            fi

            # Navigate back to the parent directory
            cd ..
        fi
    fi
done
