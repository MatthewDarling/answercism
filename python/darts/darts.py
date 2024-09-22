def score(x, y):
    if abs(x)**2 + abs(y)**2 <= 1:
        return 10
    if abs(x)**2 + abs(y)**2 <= 5**2:
        return 5
    if abs(x)**2 + abs(y)**2 <= 10**2:
        return 1
    return 0
