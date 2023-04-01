import os
import re

# Define the path to the folder containing the markdown files
folder_path = "chat_history"

# Define the name of the output file
output_file = "readme.md"

# Initialize an empty string to store the extracted content
combined_content = ""

# Loop through the markdown files (1.md to 50.md)
for i in range(1, 51):
    file_name = f"{i}.md"
    file_path = os.path.join(folder_path, file_name)

    # Check if the file exists
    if os.path.exists(file_path):
        # Read the contents of the file
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()

        # Use regex to extract content under the ## result section
        result_section = re.search(r"##\s*result(?:.*?)(?=##|$)", content, re.IGNORECASE | re.DOTALL)

        # If the result section is found, append its content to the combined_content string
        if result_section:
            extracted_content = result_section.group(0)
            combined_content += extracted_content + "\n\n"

# Write the combined content to result.md
with open(output_file, 'w', encoding='utf-8') as f:
    f.write(combined_content)

print(f"Combined content from 1.md to 50.md has been saved in {output_file}.")
