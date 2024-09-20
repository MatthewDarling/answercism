import re

def is_valid(isbn):
    normalized = isbn.replace("-", "")
    if len(normalized) != 10 or not (normalized.isdigit() or normalized[-1] == "X"):
        return False
        
    values = []
    for index, char in enumerate(normalized):
        if char.isdigit():
            values.append(int(char) * (10 - index))
        else:
            values.append(10)
            
    return sum(values) % 11 == 0