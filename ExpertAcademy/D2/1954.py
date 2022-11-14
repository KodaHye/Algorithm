# 달팽이 숫자

T = int(input())

for test_case in range(1, T + 1):
    # 우, 하, 좌, 상
    steps = [(1, 0), (0, -1), (-1, 0), (1, 0)]