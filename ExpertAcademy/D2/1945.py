# 간단한 소인수분해

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    numList = [2, 3, 5, 7, 11]
    smallNum = [] # 소인수 분해 했을 때 a, b, c, d, e 값을 저장할 배열
    for num in numList:
        count = 0
        while(n % num == 0):
            n = int(n / num)
            count += 1
        smallNum.append(count)

    print("#%d" %(test_case), end = ' ')
    print(' '.join(map(str, smallNum)))