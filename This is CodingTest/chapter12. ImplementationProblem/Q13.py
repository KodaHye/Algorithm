# 치킨 배달
# 코드 보면서 공부하기!

# input
# 5 3
# 0 0 1 0 0
# 0 0 2 0 1
# 0 1 2 0 0
# 0 0 1 0 0
# 0 0 0 0 2

from itertools import combinations

n, m = map(int, input().split())
home, chicken = [], []
distSum = 0

for i in range(n):
    data = list(map(int, input().split()))
    for j in range(n):
        if(data[j] == 1): # 집일 경우
            home.append((i, j))
        elif(data[j] == 2): # 치킨 집일 경우
            chicken.append((i, j))

# 모든 치킨집 중에서 m개의 치킨집을 뽑는 조합 계산
candinates = list(combinations(chicken, m))

# 치킨 거리의 합을 계산하는 함수
def get_sum(chicken):
    result = 0
    # 모든 집에 대하여
    for hx, hy in home:
        # 가장 가까운 치킨집 찾기
        temp = 1e9
        for cx, cy in chicken:
            temp = min(temp, abs(hx - cx) + abs(hy - cy))
        # 가장 가까운 치킨집까지의 거리를 더하기
        result += temp
    # 치킨 거리의 합 반환
    return result

# 치킨 거리의 합의 최소를 찾아 출력
result = 1e9
for candinate in candinates:
    result = min(result, get_sum(candinate))

print(result)