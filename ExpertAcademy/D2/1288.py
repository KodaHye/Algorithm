# 새로운 불면증 치료법
T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    tmp = str(n)
    numList = [0] * 10
    count = 1

    while(True):
        tmp = n * count
        tmp = str(tmp)

        for j in tmp:
            numList[int(j)] = 1
            tmp = int(n)
        if(0 not in numList):
            tmp = n * count
            break

        count += 1

    print("#%d %d" %(test_case, tmp))