def get_frequency(s: str) -> dict:
    frequency = {}
    for character in s:
        frequency[character] = frequency.get(character, 0) + 1
    return frequency


def share_common_substring(s1: str, s2: str) -> bool:
    s1_char_frequencies = get_frequency(s1)
    s2_char_frequencies = get_frequency(s2)
    for i in range(26):
        character = chr(ord('a') + i)
        if s1_char_frequencies.get(character, 0) > 0 and s2_char_frequencies.get(character, 0) > 0:
            return True
    return False


test_cases = int(input())
for _ in range(test_cases):
    s1 = input()
    s2 = input()
    print('YES' if share_common_substring(s1, s2) else 'NO')
