length, queries = map(int, input().split())
array = [0] * (length + 1)
for _ in range(queries):
    a, b, k = map(int, input().split())
    array[a - 1] += k
    array[b] -= k

max_value = 0
sum = 0
for element in array:
    sum += element
    max_value = max(max_value, sum)
print(max_value)
