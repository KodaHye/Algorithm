# 백만 장자 프로젝트

# 1. 원재는 연속된 N일 동안의 물건의 매매가를 예측하여 알고 있다.
# 2. 당국의 감시망에 걸리지 않기 위해 하루에 최대 1만큼 구입할 수 있다.
# 3. 판매는 얼마든지 할 수 있다.

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    lst= list(map(int, input().split()))
    product = 0
    phaseLst = [0] * n

    for i in range(len(lst)):
        for j in range(i + 1, len(lst)):
            if(lst[i] < lst[j]):
                phaseLst[i:j] = list(map(int, str(1) * (j - i)))
                product = j - i
                phaseLst[j] = -(j
                                -i+1)
                break

    print(lst)
    print(phaseLst)
    # print(sum(a * b for a, b in zip(lst, phaseLst)))
