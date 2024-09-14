def square_root(number):
    if number <= 0:
        raise ValueError

    # So after staring at the linked Wikipedia page for a while...
    # I submitted a cheating solution with math.isqrt
    # And then learned from the community solutions they only expected you to find the square roots of perfect squares
    # i.e. whole number results only
    # Making this problem very easy!
    for i in range(number + 1):
        if i*i == number:
            return i