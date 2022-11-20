# Sum

T = 10

for test_case in range(1, T + 1):
    lst = []
    horSum = 0
    horTmpSum = 0
    diaRightSum = 0
    diaLeftSum = 0
    n = int(input())
    for i in range(100):
        lst.append(list(map(int, input().split())))

    lstTmp = list(zip(*lst))

    for i in range(100):
        horSumTmp = sum(lst[i])
        horTmpSumTmp = sum(lstTmp[i])
        for j in range(100):
            if(i == j):
                diaRightSum += lst[i][j]
                diaLeftSum += lst[i][100-j-1]
        horSum = max(horSum, horSumTmp)
        horTmpSum = max(horTmpSum, horTmpSumTmp)
    print('#%d %d' %(test_case, max(horSum, horTmpSum, diaRightSum, diaLeftSum)))
