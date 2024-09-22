"""
This exercise stub and the test suite contain several enumerated constants.

Enumerated constants can be done with a NAME assigned to an arbitrary,
but unique value. An integer is traditionally used because it’s memory
efficient.
It is a common practice to export both constants and functions that work with
those constants (ex. the constants in the os, subprocess and re modules).

You can learn more here: https://en.wikipedia.org/wiki/Enumerated_type
"""

# Possible sublist categories.
# Change the values as you see fit.
SUBLIST = 1
SUPERLIST = 2
EQUAL = 3
UNEQUAL = 4

def sub_helper(list_one, list_two):
    len_one = len(list_one)
    len_two = len(list_two)
    for slice_start in range(0, len_two-len_one+1):
        if list_one == list_two[slice_start:slice_start+len_one]:
            return True
    return False


def sublist(list_one, list_two):
    len_one = len(list_one)
    len_two = len(list_two)
    if len_one == len_two and list_one == list_two:
        return EQUAL
    if len_one < len_two and sub_helper(list_one, list_two):
        return SUBLIST
    if len_one > len_two and sub_helper(list_two, list_one):
        return SUPERLIST
    return UNEQUAL
