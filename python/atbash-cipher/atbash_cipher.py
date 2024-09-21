from string import ascii_lowercase
import re

cipher = str.maketrans(ascii_lowercase, ascii_lowercase[::-1])

def encode(plain_text):
    ciphered = re.sub("\W", "", plain_text).replace("_", "").lower().translate(cipher)
    return " ".join(ciphered[x:x+5] for x in range(0, len(ciphered), 5))


def decode(ciphered_text):
    return ciphered_text.replace(" ", "").translate(cipher)
