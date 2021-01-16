from typing import List


def max_hourglass_sum(matrix: List[List[int]]) -> int:
    max_sum = -float('inf')
    for row in range(4):
        for column in range(4):
            max_sum = max(max_sum, hourglass_sum(matrix, row, column))
    return max_sum


def hourglass_sum(matrix: List[List[int]], row: int, column: int) -> int:
    return sum(matrix[row][column + i] + matrix[row + 2][column + i] for i in range(3)) + matrix[row + 1][column + 1]


matrix = []
for _ in range(6):
    matrix.append(list(map(int, input().split())))

print(max_hourglass_sum(matrix))
