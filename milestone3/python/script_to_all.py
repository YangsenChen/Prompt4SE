import json

# read the JSON data from file
with open('dataset/python_all.json', 'r') as file:
    data = json.load(file)

temp = ['0','1','2','3','4','5','6','7','8','9','10','11','12']

for key,value in data.items():
 if key not in temp:
    with open('chat_history/'+key+'.md', 'w') as file:
        if key == "11":
            continue
        prompts = []

        # prompts.append(
        # '''Now with the following code
        # ```python
        # %s
        # ```
        # Can you give me a reasonable unit test for the method?'''
        # % (original_string))

        prompts.append(
        '''
        Can you a line by line reasoning of the code below
        ```python
        %s
        ```
        '''
        % (value))

        prompts.append(
        '''
        Now change the data flow of the code, and provides me with a line by line reasoning of the new code. When changing the data flow, the output is not necessarily to keep unchanged. You can add arithmatic operations to the original data, but remember only change the original function, don't use external libraries or create new classes.
        ''')

        prompts.append(
        '''
        Now change the control flow of the code, and provides me with a line by line reasoning of the new code. When chaning the control flow, the output is not necessarily to keep unchanged. Remember only change the original function, don't use any external libraries or create new classes.
        ''')

        import openai

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
                       ] + conversation_history
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
        file.write(chatstr)

    # with open('chat_history/10.md', 'w') as file:
    #     file.write(chatstr)