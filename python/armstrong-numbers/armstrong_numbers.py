def is_armstrong_number(number):
    int_digits = [int(digit) for digit in str(number)]
    return number == sum([digit ** len(int_digits) for digit in int_digits])
