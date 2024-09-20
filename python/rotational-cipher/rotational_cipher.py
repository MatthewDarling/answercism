def rot_plus(boundary, char, key):
    combined = char + key
    if combined > boundary:
        return combined - 26
    return combined

def rotate_char(char, key):
    converted = ord(char)
    if ord("a") <= converted <= ord("z"):
        return chr(rot_plus(ord("z"), converted, key))
    if ord("A") <= converted <= ord("Z"):
        return chr(rot_plus(ord("Z"), converted, key))
    return char

def rotate(text, key):
    return "".join([rotate_char(char, key) for char in text])

def rotate_translate(text, key):
    "Use Python's <str>.translate method to rotate `text`. I like this a lot more than the ASCII code solution, especially the slicing by `key` to create the rotated character set. Taken from https://exercism.org/tracks/python/exercises/rotational-cipher/approaches/str-translate"
    alphabet = "abcdefghijklmnopqrstuvwxyz"
    translator = alphabet[key:] + alphabet[:key]
    return text.translate(str.maketrans(alphabet + alphabet.upper(), translator + translator.upper()))