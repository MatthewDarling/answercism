from string import ascii_uppercase

def rows(letter):
    letter_num = ascii_uppercase.index(letter) + 1
    max_width = (letter_num * 2) - 1

    output = []
    for index, letter in enumerate(ascii_uppercase[0:letter_num]):
        middle_spaces = "" if not index else " " * (2 * (index - 1) + 1)
        uncentred = letter + middle_spaces + (letter if index else "")
        output.append(uncentred.center(max_width))

    output.extend(reversed(output[:-1]))
    
    return output