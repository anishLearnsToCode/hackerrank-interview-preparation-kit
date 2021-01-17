# Longest common subsequence problem (LCS)

s1 = input()
s2 = input()

s1, s2 = (s1, s2) if len(s1) > len(s2) else (s2, s1)

rows, columns = 2, len(s2) + 1
table = [[0] * columns for _ in range(rows)]
i = 0

for row in range(1, len(s1) + 1):
    for column in range(1, columns):
        table[i][column] = max(
            table[i][column - 1],
            table[i ^ 1][column],
            table[i ^ 1][column - 1] + int(s1[row - 1] == s2[column - 1])
        )
    i ^= 1

print(table[i ^ 1][columns - 1])
