from typing import Dict


def get_frequency(iter) -> Dict:
    frequency = {}
    for item in iter:
        frequency[item] = frequency.get(item, 0) + 1
    return frequency


def is_valid(string: str) -> bool:
    character_frequency = get_frequency(string)
    distribution = get_frequency(character_frequency.values())
    if len(distribution) == 1: return True
    if len(distribution) > 2: return False
    a, b = list(distribution.keys())
    return (abs(a - b) == 1 and distribution[max(a, b)] == 1) or (min(a, b) == 1 and distribution[1] == 1)


string = input()
print('YES' if is_valid(string) else 'NO')
