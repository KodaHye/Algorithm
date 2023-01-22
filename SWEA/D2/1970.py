# 쉬운 거스름돈

T = int(input())

for test_case in range(1, T + 1):
    moneyList = [50000, 10000, 5000, 1000, 500, 100, 50, 10]
    changeList = [0] * 8

    n = int(input())

    for i in range(len(moneyList)):
        changeList[i] = n // moneyList[i]
        n %= moneyList[i]

    print('#%d' %(test_case))
    print(' '.join(map(str, changeList)))