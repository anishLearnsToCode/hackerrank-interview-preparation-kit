from typing import List


def get_mapping(numbers: List[int]) -> dict:
    frequency = {}
    for index, element in enumerate(numbers):
        frequency[element] = index
    return frequency


def minimum_swaps(numbers: List[int]) -> int:
    swaps = 0
    mapping = get_mapping(numbers)
    for element in range(1, len(numbers) + 1):
        if element != mapping[element] + 1:
            element_index = mapping[element]
            numbers[element - 1], numbers[element_index] = element, numbers[element - 1]
            mapping[element] = element - 1
            mapping[numbers[element_index]] = element_index
            swaps += 1
    return swaps


input()
numbers = list(map(int, input().split()))
print(minimum_swaps(numbers))
