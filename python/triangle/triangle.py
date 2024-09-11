def valid_sides(sides):
    return all(sides) #true iff every value in sides is true


def one_side_shorter(sides):
    return (sides[0] + sides[1] >= sides[2]) and (sides[1] + sides[2] >= sides[0]) and (sides[0] + sides[2] >= sides[1])


def is_triangle(sides):
    return valid_sides(sides) and one_side_shorter(sides)


def equilateral(sides):
    return is_triangle(sides) and len(set(sides)) == 1


def isosceles(sides):
    return is_triangle(sides) and len(set(sides)) < 3


def scalene(sides):
    return is_triangle(sides) and len(set(sides)) == 3
