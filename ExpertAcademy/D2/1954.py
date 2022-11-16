# 달팽이 숫자

# 배열 index 잘 생각하기
# 차근차근 생각하기!!

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    x, y = 0, 0
    array = [[0] * n for _ in range(n)]

    # 우, 하, 좌, 상
    steps = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    step = 0

    for i in range(1, n * n+1):
        array[x][y] = i

        x += steps[step % 4][0]
        y += steps[step % 4][1]

        if x < 0 or y < 0 or x >= n or y >= n or array[x][y] != 0:
            # 실행취소
            x -= steps[step % 4][0]
            y -= steps[step % 4][1]

            step = (step + 1) % 4

            x += steps[step % 4][0]
            y += steps[step % 4][1]

    print("#%d" %(test_case), end =' ')
