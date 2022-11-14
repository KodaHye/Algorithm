# 거스름돈
# 거스름돈으로 사용할 500원, 100원, 50원, 10원 짜리 동전이 존재
# 거스름돈이 N원일 때 거슬러 줘야되는 동전의 최소 개수

change = int(input())
count = 0

# 큰 단위의 화폐부터 차례대로 확인
coinTypes = [500, 100, 50, 10]

for coin in coinTypes:
    count += change // coin # 해당 화폐로 거슬러 줄 수 있는 동전의 개수 세기
    change %= coin

print(count)