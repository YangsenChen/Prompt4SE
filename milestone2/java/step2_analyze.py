import os
import re

# Define the path to the folder containing the markdown files
folder_path = "chat_history"

# Initialize counters for success and fail times
success_count = 0
fail_count = 0
total_tests_passed = 0
total_tests_attempted = 0
success_files = []
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

        # If the result section is found, count success and fail occurrences
        if result_section:
            extracted_content = result_section.group(0)

            if len(re.findall(r"(success|succeed)", extracted_content, re.IGNORECASE)) >= 1:
                success_count += 1
                success_files.append(file_name)
            else:
                fail_count += len(re.findall(r"fail", extracted_content, re.IGNORECASE))

            # Extract pass rates from successful results
            pass_rates = re.findall(r"(\d+)/(\d+)", extracted_content)

            for passed, attempted in pass_rates:
                total_tests_passed += int(passed)
                total_tests_attempted += int(attempted)

# Calculate success rate
total_attempts = success_count + fail_count
if total_attempts > 0:
    success_rate = (success_count / total_attempts) * 100
else:
    success_rate = 0

# Calculate total test pass rate
if total_tests_attempted > 0:
    test_pass_rate = (total_tests_passed / total_tests_attempted) * 100
else:
    test_pass_rate = 0

print(f"Success count: {success_count}")
print(f"Fail count: {fail_count}")
print(f"Success rate: {success_rate:.2f}%")
print(f"Total tests passed: {total_tests_passed}")
print(f"Total tests compilable: {total_tests_attempted}")
print(f"Total test pass rate: {test_pass_rate:.2f}%")
print(f"Success files: {success_files}")