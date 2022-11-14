# 스도쿠 검증

T = int(input())

for test_case in range(1, T + 1):
    puzzle = [[0] * 9 for _ in range(9)]

    for i in range(0, 9):
        puzzle[i] = list(map(int, input().split()))
