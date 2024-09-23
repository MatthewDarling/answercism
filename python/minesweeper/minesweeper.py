def annotate(minefield):
    if any(len(x) != len(minefield[0]) for x in minefield):
        raise ValueError("The board is invalid with current input.")
    if minefield and minefield[0] and not all(y == "*" or y == " " for x in minefield for y in x):
        raise ValueError("The board is invalid with current input.")

    for column_index, row in enumerate(minefield):
        row_list = list(row)
        for row_index, item in enumerate(row):
            if item == "*":
                continue
            squares_to_check = list(zip(range(column_index-1, column_index+2), [row_index-1]*3)) + list(zip([column_index-1, column_index+1], [row_index]*2)) + list(zip(range(column_index-1, column_index+2), [row_index+1]*3))
            squares_to_check = [x for x in squares_to_check if x[0] >= 0 and x[1] >= 0 and x[0] < len(minefield) and x[1] < len(row_list)]
            adjacent_mines = len([x for x in squares_to_check if minefield[x[0]][x[1]] == "*"])
            row_list[row_index] = " " if not adjacent_mines else str(adjacent_mines)
        minefield[column_index] = "".join(row_list)
    
    return minefield