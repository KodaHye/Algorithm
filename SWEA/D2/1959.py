# 두 개의 숫자열

T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    max = 0

    if(len(a) > len(b)):
        a, b = b, a

    for i in range(0, len(b) - len(a) + 1):
        sum = 0
        for j in range(0, len(a)):
            sum += a[j] * b[j + i]
        if(sum > max):
            max = sum
    print("#%d %d" %(test_case, max))