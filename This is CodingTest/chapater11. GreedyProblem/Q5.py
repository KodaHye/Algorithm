# 볼링공 고르기

n, m = map(int, input().split())
lst = list(map(int, input().split()))
countLst = [0] * (m  + 1)
count = 0

for i in range(0, len(lst)):
    countLst[lst[i]] += 1

for i in range(1, len(countLst) - 1):
    temp = 0
    for j in range(i + 1, len(countLst)):
        temp += countLst[j]

    temp  = countLst[i] * temp
    count += temp

print(count)