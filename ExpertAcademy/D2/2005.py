# 파스칼의 삼각형

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    paskal = [[] * n for _ in range(n)]

    for i in range(1, n + 1):
        for j in range(0, i):
            if(j <= i + 1):
                paskal[i - 1].append(1)
                if(j > 0 and j < i-1) :
                    paskal[i - 1][j] = paskal[i - 2][j - 1] + paskal[i - 2][j]

    print("#%d" %(test_case))
    for j in paskal:
        for k in j:
            print(k, end = ' ')
        print()
