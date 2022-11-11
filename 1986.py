T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    inputNum = int(input())
    initNum = 1
    evenSum = 0
    oddSum = 0

    while(initNum <= inputNum):
        if(initNum % 2 == 0):
            evenSum += initNum
        else:
            oddSum += initNum
        initNum += 1

    print("#%d %d" % (test_case, oddSum - evenSum))
