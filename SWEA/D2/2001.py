# 파리 퇴치

T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())
    max = 0

    flyList = [[0] * n for _ in range(n)]

    for i in range(n):
        flyList[i] = list(map(int, input().split()))

    for j in range(0, n - m  + 1):
        for k in range(0, n - m  + 1):
            killFly = 0
            for l in range(m):
                killFly += sum(flyList[j+l][k:k+m])
            if(killFly > max):
                max = killFly

    print("#%d %d" %(test_case, max))
