# 이것이 코딩테스트다
# p.180

n = int(input())
nameScoreList = []

for i in range(0, n):
    nameScore = input().split()
    nameScoreList.append((nameScore[0], int(nameScore[1])))

nameScoreList = sorted(nameScoreList, key = lambda x: x[1])

for name in nameScoreList:
    print(name[0], end = ' ')
