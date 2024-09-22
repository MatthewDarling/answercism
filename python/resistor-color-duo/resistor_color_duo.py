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

def value(colors):
    return int("".join(str(color_code.index(x)) for x in colors[:2]))
