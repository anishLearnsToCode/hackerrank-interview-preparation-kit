n = int(input())
clouds = list(map(int, input().split()))
min_steps = [0] * n
infinity = float('inf')
min_steps[n - 2] = infinity if clouds[n - 2] == 1 else 1

for index in range(n - 3, -1, -1):
    if clouds[index] == 1:
        min_steps[index] = infinity
    else:
        min_steps[index] = 1 + min(min_steps[index + 1], min_steps[index + 2])

print(min_steps[0])
