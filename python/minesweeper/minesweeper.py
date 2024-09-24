def annotate(minefield):
    if any(len(x) != len(minefield[0]) for x in minefield):
        raise ValueError("The board is invalid with current input.")

    for column_index, row in enumerate(minefield):
        row_list = list(row)
        for row_index, item in enumerate(row):
            if item == "*":
                continue
            if item != " ":
                raise ValueError("The board is invalid with current input.")

            mine_neighbours = 0
            for neighbour in [(x,y) for x in range(column_index-1, column_index+2) for y in range(row_index-1, row_index+2)]:
                if neighbour[0] >= 0 and neighbour[0] < len(minefield) and neighbour[1] >= 0 and neighbour[1] < len(row_list) and neighbour != (column_index, row_index) and minefield[neighbour[0]][neighbour[1]] == "*":
                    mine_neighbours += 1

            row_list[row_index] = str(mine_neighbours or " ")
        minefield[column_index] = "".join(row_list)
    
    return minefield
