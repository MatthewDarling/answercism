def rebase(input_base, digits, output_base):
    if input_base < 2:
        raise ValueError("input base must be >= 2")
    if output_base < 2:
        raise ValueError("output base must be >= 2")
    if [x for x in digits if x < 0 or x >= input_base]:
        raise ValueError("all digits must satisfy 0 <= d < input base")

    as_base_10 = 0
    for digit in digits:
        as_base_10 *= input_base
        as_base_10 += digit

    output = []
    while as_base_10:
        as_base_10, new_digit = divmod(as_base_10, output_base)
        output.append(new_digit)

    output.reverse()
    
    return output or [0]