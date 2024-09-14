import re

add_ay_pattern = re.compile("^([aeiou]|xr|yt)")
qu_start_pattern = re.compile("^([^aeiou]*qu)(.*)")
y_start_pattern = re.compile("^([^aeiou]+)(y.*)")
consonant_start_pattern = re.compile("^([^aeiou]+)(.*)")

replace_pattern = r'\2\1ay'

def translate_one_word(word):
    if add_ay_pattern.search(word):
        return word + "ay"
    if qu_start_pattern.search(word):
        return re.sub(qu_start_pattern, replace_pattern, word)
    if y_start_pattern.search(word):
        return re.sub(y_start_pattern, replace_pattern, word)
    if consonant_start_pattern.search(word):
        return re.sub(consonant_start_pattern, replace_pattern, word)

def translate(text):
    return " ".join([translate_one_word(word) for word in text.split()])
