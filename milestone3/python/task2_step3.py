import os
import glob
from bs4 import BeautifulSoup

# Define the root directory
root_dir = "./generated_code/"

# Iterate over all the folders from code_00 to code_49
for i in range(50):
    folder_name = f"code_{i:02d}"
    target_folder = os.path.join(root_dir, folder_name, "report/mutants")

    # Search for all html files in the target folder
    html_files = glob.glob(os.path.join(target_folder, "*.html"))

    # Continue only if there are html files
    if html_files:
        output_folder = os.path.join(root_dir, folder_name, "mutants")
        os.makedirs(output_folder, exist_ok=True)  # create output folder if it doesn't exist

        for file in html_files:
            with open(file, 'r') as f:
                soup = BeautifulSoup(f, 'html.parser')

                # Find the <h3> with "Mutant" in it
                h3 = soup.find('h3', string='Mutant')

                # If it exists, find the next <pre> tag
                if h3:
                    pre = h3.find_next('pre')

                    # If the <pre> tag exists, get the code
                    if pre:
                        code = pre.get_text()

                        # Define the output file name (with .py extension)
                        output_file = os.path.join(output_folder,
                                                   f"{folder_name}_mutant_{os.path.splitext(os.path.basename(file))[0]}.py")

                        # Write the code to the output file
                        with open(output_file, 'w') as out_file:
                            out_file.write(code)
