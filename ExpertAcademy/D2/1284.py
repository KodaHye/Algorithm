# 수도 요금 경쟁
# 두 회사 중 더 적게 수도 요금을 부담해도 되는 회사
# A사: 1리터당 P원
# B사: 기본요금 Q원, 월간 사용량이 R리터 이하인 경우 요금은 기본 요금만
#      R 리터보다 많은 양을 사용한 경우 초과량에 대해 1리터당 S원의 요금

T = int(input())
for test_case in range(1, T + 1):
    p, q, r, s, w = map(int, input().split())
    outputA = p * w

    if(w <= r):
        outputB = q
    else:
        outputB = q + (w - r) * s

    result = min(outputA, outputB)

    print("#%d %d" % (test_case, result))