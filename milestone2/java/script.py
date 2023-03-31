

import json

# read the JSON data from file
with open('dataset/1.json', 'r') as file:
    data = json.load(file)
    original_string = data['original_string']
    func_name = data['func_name'].split('.')[-1]

# print(original_string)
# print(func_name)

prompts = []

prompts.append(
'''```java
%s
```

Where is this function from?
''' % original_string)

prompts.append(
''' ```java\n%s\n```

1. reason about code execution of this code

2. explain the functionality of this code'''
% original_string)

prompts.append(
'''Now I want you to act as a senior programmer.

The most important thing is to only show me the code.

```java
%s
```

generate a Main class wrapping this code and add public static void main to use this code snippet.

you can self implement any class that you think is needed.

and the most important thing is that do not use any third party library.
and the most important thing is do not change the content of %s.'''
% (original_string, func_name))

prompts.append(
'''append three unit test methods inside the Main class.

each test method should start with @test''')






import openai
import json

# Replace "your_api_key_here" with your actual API key
openai.api_key = "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"

# Initialize an empty list to store conversation history
conversation_history = []

def send_message_to_chatgpt(message):
    # conversation_history.append({'role': 'system', 'content': 'start the chat'})
    conversation_history.append({'role': 'user', 'content': message})

    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
                   {'role': 'system', 'content': 'You are ChatGPT, a large language model trained by OpenAI, based on the GPT-4 architecture.'}
               ] + conversation_history,
        # max_tokens=150,
        # n=1,
        # stop=None,
        # temperature=0.7,
    )

    assistant_message = response.choices[0].message.content
    conversation_history.append({'role': 'assistant', 'content': assistant_message})
    return assistant_message

# Example usage of the send_message_to_chatgpt function
# import pyperclip
chat = {}
chatstr = ""
for i,q in enumerate(prompts):

    chat.update([("Q" + str(i), q)])
    chatstr += "\n\n## user: \n\n" + q
    # pyperclip.copy(chatstr)

    print("\n\n## user: \n\n", q)

    a = send_message_to_chatgpt(q)

    chat.update([("A" + str(i), a)])
    chatstr += "\n\n## chatgpt: \n\n"+ a
    print("\n\n## chatgpt: \n\n", a)

# with open('5_r.json', 'w') as file:
#     json.dump(chat, file, indent=4)

#save chatstr to markdown file
with open('chat_history/1.md', 'w') as file:
    file.write(chatstr)





