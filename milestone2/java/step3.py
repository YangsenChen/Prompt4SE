import re

# def parse_md(filename):
#     with open(filename, 'r') as file:
#         content = file.read()
#
#     return re.split("## chatgpt:|## user:|## result", content)

def process_md_data(filename):

    with open(filename, 'r') as file:
        content = file.read()

    parsed_data = re.split("## chatgpt:|## user:|## result", content)[1:-1]

    formatted_data = []
    for i, data in enumerate(parsed_data):
        formatted_data.append({'role': 'user' if i % 2 == 0 else 'assistant', 'content': data.strip()})

    return formatted_data


if __name__ == '__main__':
    filename = './chat_history/1.md'
    # parsed_data = parse_md(filename)[1:-1]
    formatted_data = process_md_data(filename)
    print(formatted_data)
