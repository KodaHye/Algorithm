# 모험가 길드

n = int(input())
lst = list(map(int, input().split()))

init = 0
count = 0
lst.sort(reverse=True)

while(n > 0):
    count += 1
    n -= lst[init]
    init += (lst[init] - 1)

print(count)