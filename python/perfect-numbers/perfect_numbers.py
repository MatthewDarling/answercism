def factors(number):
    # borrowed from community solutions, still slow but only half as slow as going the full range :)
    highest_possible_factor = (number // 2) + 1
    return [x for x in range(1, highest_possible_factor) if number % x == 0]

def classify(number):
    """ A perfect number equals the sum of its positive divisors.

    :param number: int a positive integer
    :return: str the classification of the input integer
    """
    if number <= 0:
        raise ValueError("Classification is only possible for positive integers.")
    
    aliquot = sum(factors(number))
    if aliquot == number:
        return "perfect"
    if aliquot > number:
        return "abundant"
    return "deficient"
