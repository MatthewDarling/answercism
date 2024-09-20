import operator, numbers

valid_ops = {"plus": operator.add, "minus": operator.sub, "multiplied_by": operator.mul, "divided_by": operator.truediv}


def is_num(text):
    abs = text[1:] if text.startswith("-") else text
    return abs.isdigit()


# I _think_ with the [x for x in seq if condition] syntax,
# separating a boolean check from the converting is ideal
# even though it is duplicating the conditionals
def important_piece(text):
    if is_num(text) or valid_ops.get(text):
        return text
    if text not in {"What", "is"}:
        raise ValueError("unknown operation")


def convert_piece(text):
    if is_num(text):
        return int(text)
    return text


def evaluate_pieces(pieces):
    """My original solution assumed BEDMAS type ordering, having to perform multiplications and divisions before addition and subtraction. So I had a complicated version of this with different sets of operators, finding the first operator for a set and evaluating it, moving on to the next set, etc. But I eventually realized it was actually just expecting left-to-right evaluation, and had a working solution with a bunch of helper functions. But the 'dig deeper' article showed a simpler way to do it using destructuring, so I've swapped to that instead. Same idea, just less complicated."""
    result = pieces.copy()

    while len(result) > 1:
        try:
            x, op, y, *rest = result
            if op not in valid_ops.keys(): raise ValueError("syntax error")
            result = [valid_ops.get(op)(x, y), *rest]
        except:
            raise ValueError("syntax error")

    if len(result) == 1:
        return result[0]

    raise ValueError("syntax error")
    

def answer(question):
    if not question.endswith("?"):
        raise ValueError("syntax error")

    question = question.replace(" by", "_by")[:-1]
    post_split = question.split()

    pieces = [convert_piece(piece) for piece in post_split if important_piece(piece)]

    return evaluate_pieces(pieces)

    
#### Helper functions from my original solution, for comparison
def find_ops(pieces, ops):
    for index, elem in enumerate(pieces):
        if ops.get(elem):
            return index


def evaluate_op(pieces, target_index, ops):
    pieces[target_index-1:target_index+2] = [ops.get(pieces[target_index])(pieces[target_index-1], pieces[target_index+1])]


def evaluate_pieces_old(pieces):
    result = pieces.copy()

    # Originally I had two loops and two dictionaries, one for * / and one for + -
    while index := find_ops(result, valid_ops):
        evaluate_op(result, index, valid_ops)

    if len(result) == 1:
        return result[0]
        
    raise ValueError("syntax error")