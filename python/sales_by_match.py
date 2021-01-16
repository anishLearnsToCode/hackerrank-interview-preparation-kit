n = int(input())
socks = list(map(int, input().split()))

socks_frequency = {}
for sock in socks:
    socks_frequency[sock] = socks_frequency.get(sock, 0) + 1

pairs = 0
for sock_number, freq in socks_frequency.items():
    pairs += freq // 2

print(pairs)
