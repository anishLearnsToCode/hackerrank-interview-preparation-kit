n, rotation = map(int, input().split())
array = list(map(int, input().split()))
rotation %= n

array = array[rotation:] + array[:rotation]
for element in array:
    print(element, end=' ')
