# 숫자 카드 게임

n, m = map(int, input().split())

cards = [[0] * n for _ in range(m)]
minCards = []

for i in range(n):
    cards[i] = list(map(int, input().split()))
    minCards.append(min(cards[i]))

print(max(minCards))