# 어디에 단어가 들어갈 수 있을까

T = int(input())

for test_case in range(1, T + 1):
    n, k = map(int, input().split())
    count = 0
    quzzle = [[0] * (n + 4) for _ in range(n+4)]
    for i in range(len(quzzle) - 4):
        quzzle[i + 2][2:2+n] = map(int, input().split())

    quzzleTrans = list(zip(*quzzle))

    for i in range(2, n + 2):
        for j in range(2, n + 4 - (k + 2 - 1)):
            # print(j)
            # print(''.join(map(str, quzzle[i][j-1:j+4])))
            if(''.join(map(str, quzzle[i][j-1:j+k + 1])) == '0' + '1' * k + '0'):
                count += 1
            if(''.join(map(str, quzzleTrans[i][j-1:j+ k + 1])) == '0' + '1' * k + '0'):
                count += 1
    print("#%d %d" % (test_case, count))