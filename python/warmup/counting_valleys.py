input()
terrain = input()
height = 0
in_valley = False
valleys = 0

for character in terrain:
    height += 1 if character == 'U' else -1

    if in_valley:
        in_valley = height < 0
    elif height < 0:
        valleys += 1
        in_valley = True

print(valleys)
