color_code = ["black",
              "brown",
              "red",
              "orange",
              "yellow",
              "green",
              "blue",
              "violet",
              "grey",
              "white"]

resistance_code = {"grey": "0.05",
                   "violet": "0.1",
                   "blue": "0.25",
                   "green": "0.5",
                   "brown": "1",
                   "red": "2",
                   "gold": "5",
                   "silver": "10"}

prefixes = ["", "kilo", "mega", "giga"]

def is_whole(n):
    """True if n is a whole number, i.e. casts safely to an integer. Floats do have an is_integer method but this works if n is already an integer."""
    return n % 1 == 0

def resistor_label(colors):
    if len(colors) > 3:
        *values, multiplier, tolerance = colors
    else:
        values, multiplier, tolerance = colors, 0, None
    
    base = int("".join(str(color_code.index(x)) for x in values))
    ohms = base
    prefix_index = 0

    if len(colors) > 3:
        ohms = base * (10 ** color_code.index(multiplier))

        while ohms >= 1000:
            ohms /= 1000
            prefix_index += 1

    if is_whole(ohms):
        ohms = int(ohms)

    output = f"{ohms} {prefixes[prefix_index]}ohms"
    if tolerance:
        output += f" ±{resistance_code[tolerance]}%"
        
    return output