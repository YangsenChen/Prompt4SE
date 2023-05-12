import os
import re

# Define the path to the directory
dir_path = '/Users/yangsen/Desktop/CS598ML4SE/milestone3/java/chat_history/task1'

# Regular expression to find code blocks
code_block_pattern = re.compile(r'```java(.*?)```', re.DOTALL)

# Loop over all files in the directory
for filename in os.listdir(dir_path):
    if filename.endswith(".md"):
        with open(os.path.join(dir_path, filename), 'r') as file:
            file_content = file.read()

            # Find all code blocks in the file
            code_blocks = re.findall(code_block_pattern, file_content)

            # Check if we have exactly three code blocks
            if len(code_blocks) == 3:
                # Define the output directory
                output_dir = os.path.join('task1', filename.split('.')[0])
                os.makedirs(output_dir, exist_ok=True)

                # Define the output filenames
                output_filenames = ['original.java', 'modified.java', 'modified_complex.java']

                # Save each code block to a new file
                for i in range(3):
                    with open(os.path.join(output_dir, output_filenames[i]), 'w') as output_file:
                        # Write the code block to the file, removing the leading/trailing newlines
                        output_file.write(code_blocks[i].strip())
