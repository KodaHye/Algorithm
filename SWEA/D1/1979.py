# 어디에 단어가 들어갈 수 있을까

T = int(input())

for test_case in range(1, T + 1):
    n, k = map(int, input().split())

    puzzle = [[0] * (n + 2) for _ in range(0, n + 2)]
    count = 0

    for i in range(n):
        puzzle[i][0:n] = list(map(str, input().split()))

    for i in range(2, n):
        for j in range(2, (n-1) // k + n % k):
            if(''.join(map(str, puzzle[i][j:j+k+1])) == '1'* k + '0'):
                print(''.join(map(str, puzzle[i][j:j+k+1])))
                count += 1

    # for i in range(n // k + n % k):
    #     for j in range(n):
    #         if(str(puzzle[i][j]) + str(puzzle[i+1][j]) + str(puzzle[i+2][j]) + str(puzzle[i+3][j]) == '1'* k + '0'):
    #             count += 1
    print("#%d %d" % (test_case, count))
