def minimum_deletions(string: str) -> int:
    current_character = string[0]
    count = 0
    deletions = 0
    for character in string:
        if character == current_character:
            count += 1
        else:
            current_character = character
            deletions += count - 1
            count = 1
    return deletions + count - 1


test_cases = int(input())
for _ in range(test_cases):
    string = input()
    print(minimum_deletions(string))
