def get_substrings_freq(s: str) -> dict:
    distribution = {}
    for i in range(len(s)):
        frequency = {}
        for j in range(i, len(s)):
            character = s[j]
            frequency[character] = frequency.get(character, 0) + 1
            freq_repr = tuple(sorted(list(frequency.items()), key=lambda x: x[0]))
            distribution[freq_repr] = distribution.get(freq_repr, 0) + 1
    return distribution


def nC2(n: int) -> int:
    return n * (n - 1) // 2


def anagramatic_pairs(s: str) -> int:
    substrings_freq = get_substrings_freq(s)
    pairs = 0
    for value in substrings_freq.values():
        pairs += nC2(value)
    return pairs


test_cases = int(input())
for _ in range(test_cases):
    s = input()
    print(anagramatic_pairs(s))
