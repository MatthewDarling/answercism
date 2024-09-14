import re

open_for_given_close = {"]" : "[", 
                        "}": "{", 
                        ")":"("}

def is_paired(input_string):
    relevant_chars = re.sub(r'[^()[\]{}]', "", input_string)
    stack = []
    
    for char in relevant_chars:
        # cribbed the in .values() bit from the explainer article
        if char in open_for_given_close.values():
            stack.append(char)
        elif char in open_for_given_close:
            if not stack or open_for_given_close[char] != stack.pop():
                return False
                
    return not stack