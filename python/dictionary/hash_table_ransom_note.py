from typing import List


def get_frequency(s: List[str]) -> dict:
    frequency = {}
    for word in s:
        frequency[word] = frequency.get(word, 0) + 1
    return frequency


def has_characters(magazine: List[str], note: List[str]) -> bool:
    magazine_characters = get_frequency(magazine)
    note_characters = get_frequency(note)
    for word, freq in note_characters.items():
        if magazine_characters.get(word, 0) < freq:
            return False
    return True


_, _ = input().split()
magazine = input().split()
note = input().split()
print('Yes' if has_characters(magazine, note) else 'No')
