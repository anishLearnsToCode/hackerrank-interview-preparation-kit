from typing import List


def number_of_triplets(elements: List[int], r: int) -> int:
    frequency = {}
    pairs = {}
    triplets = 0
    for element in elements[::-1]:
        triplets += pairs.get(element * r, 0)
        pairs[element] = pairs.get(element, 0) + frequency.get(element * r, 0)
        frequency[element] = frequency.get(element, 0) + 1
    return triplets


n, r = map(int, input().split())
numbers = list(map(int, input().split()))
print(number_of_triplets(numbers, r))
