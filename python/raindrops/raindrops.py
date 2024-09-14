def convert(number):
    output = ""

    if not number % 3:
        output += "Pling"
    if not number % 5:
        output += "Plang"
    if not number % 7:
        output += "Plong"
    if not output:
        output = str(number)
    return output