def occurences_a(string: str) -> int:
    return sum(1 if character == 'a' else 0 for character in string)


string = input()
n = int(input())

result = (n // len(string)) * occurences_a(string) + occurences_a(string[:n % len(string)])
print(result)
