# 만들 수 없는 금액

# 알고리즘은 알겠는데, 발상하고 이해하는게 쉽지 않았다.
# x와 sum(target)을 가로로 적고 생각해보자!

# 1부터 차례로 특정한 금액을 만들 수 있는지 확인
n = int(input())
data = list(map(int, input().split()))
data.sort()

target = 1
for x in data:
    # 만들 수 없는 금액을 찾았을 때 반복 종료
    if target < x:
        break
    target += x

# 만들 수 없는 금액 출력
print(target)