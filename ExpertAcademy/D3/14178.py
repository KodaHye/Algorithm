# 1차원 정원

T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())

    result = round(((n - (m + 1)) / (2 * m + 1))) + 1
    print("#%d %d" %(test_case, result))
