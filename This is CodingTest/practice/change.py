# 이것이 코딩테스트다
# p.182

n, k = map(int, input().split())

listA = []
listB = []

listA = sorted(list(map(int, input().split())))
listB = sorted(list(map(int, input().split())), reverse = True)

for i in range(0, k):
    if(listA[i] < listB[i]):
        listA[i], listB[i] = listB[i], listA[i]

print(sum(listA))