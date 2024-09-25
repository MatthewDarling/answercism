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

prefixes = ["", "kilo", "mega", "giga"]

def label(colors):
    base = int("".join(str(color_code.index(x)) for x in colors[:2]))
    ohms = base * (10 ** color_code.index(colors[2]))

    prefix_index = 0
    while ohms >= 1000:
        ohms //= 1000
        prefix_index += 1

    return "".join([str(ohms), " ", prefixes[prefix_index], "ohms"])
        