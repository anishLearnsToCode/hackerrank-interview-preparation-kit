from typing import Dict


def get_frequency(string: str) -> Dict[str, int]:
    frequency = {}
    for character in string:
        frequency[character] = frequency.get(character, 0) + 1
    return frequency


string_1 = input()
string_2 = input()

frequency_1 = get_frequency(string_1)
frequency_2 = get_frequency(string_2)
deletions = 0

for i in range(26):
    character = chr(ord('a') + i)
    deletions += abs(frequency_1.get(character, 0) - frequency_2.get(character, 0))
print(deletions)
