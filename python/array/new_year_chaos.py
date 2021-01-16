from typing import List


def number_bribes(queue: List[int]):
    bribes = 0
    for index, element in enumerate(queue):
        if element - index - 1 > 2:
            return 'Too chaotic'
        for j in range(max(0, element - 2), index):
            bribes += 1 if queue[j] > element else 0
    return bribes


test_cases = int(input())
for _ in range(test_cases):
    input()
    queue = list(map(int, input().split()))
    print(number_bribes(queue))
