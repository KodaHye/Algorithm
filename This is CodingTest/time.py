# 이것이 코딩테스트다
# p.113

hour = int(input())
numCount = 0
for i in range(0, hour + 1):
    for j in range(60):
        for k in range(60):
            if '3' in str(i) + str(j) + str(k):
                numCount += 1

print(numCount)