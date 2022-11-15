# 달팽이 숫자

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())

    # 우, 하, 좌, 상
    steps = [(1, 0), (0, -1), (-1, 0), (1, 0)]

    snailList = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
