import re

def is_isogram(string):
    normalized = re.sub("[- ]", "", string.lower())
    return len(normalized) == len(set(normalized))