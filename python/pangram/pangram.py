import re

def is_pangram(sentence):
    normalized = re.sub("[^a-z]", "", sentence.lower())
    return len(set(normalized)) == 26
