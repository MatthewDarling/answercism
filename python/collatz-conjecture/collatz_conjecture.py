def steps(number):
    if number <= 0:
        raise ValueError("Only positive integers are allowed")
        
    step_count = 0
    while number != 1:
        step_count += 1
        if not number % 2:
            number = number / 2
        else:
            number = (number * 3) + 1
            
    return step_count