def response(hey_bob):
    normalized = hey_bob.strip()
    is_silence = normalized == ""
    is_question = not is_silence and normalized.endswith("?")
    is_yelling = not is_silence and normalized.isupper()

    if is_question and is_yelling:
        return "Calm down, I know what I'm doing!"
    if is_question:
        return "Sure."
    if is_yelling:
        return "Whoa, chill out!"
    if is_silence:
        return "Fine. Be that way!"
    return "Whatever."
