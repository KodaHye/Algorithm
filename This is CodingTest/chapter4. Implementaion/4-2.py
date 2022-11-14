# 시각
# 완전 탐색 유형
# 데이터가 100만 개 이하일 때 사용하면 적절함

n = int(input())
count = 0

for i in range(n+1):
    for j in range(60):
        for k in range(60):
            if('3' in str(i) + str(j) + str(k)):
                count += 1
print(count)