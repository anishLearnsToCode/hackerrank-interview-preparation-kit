def sum_n_natural_numbers(n: int) -> int:
    return n * (n + 1) // 2


def number_special_strings(s: str) -> int:
    special_strings = 0
    i = 0
    while i < len(s):
        repeat = 1
        while i + 1 < len(s) and (s[i] == s[i + 1]):
            repeat += 1
            i += 1
        special_strings += sum_n_natural_numbers(repeat)

        spread = 1
        while (i - spread >= 0 and i + spread < len(string)) and (s[i - spread] == s[i + spread]) and s[i - 1] == s[
            i - spread]:
            spread += 1
            special_strings += 1

        i += 1

    return special_strings


n = int(input())
string = input()
print(number_special_strings(string))
