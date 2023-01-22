# 숫자 배열 회전
# 2차원 배열에서 index 자유자재로 활용할 수 있어야 됨
# test = [list(map(int, input().split())) for _ in range(n)]
# test_90 = list(zip(*test[::-1]))

T = int(input())

for test_case in range(1, T + 1):
    def transLst(lst1, lst2):
        for i in range(len(lst1)):
            for j in range(len(lst1)):
                lst2[i][j] = lst1[n - 1 - j][i]

    n = int(input())
    lst = []
    resultLst = [[0] * 3 for _ in range(n)]
    tmpLst = [[[0] * n for _ in range(n)] for _ in range(3)]
    tmpLst2 = [[0] * n for _ in range(n)]

    for i in range(n):
        lst.append(list(map(int, input().split())))

    transLst(lst, tmpLst[0])
    transLst(tmpLst[0], tmpLst[1])
    transLst(tmpLst[1], tmpLst[2])

    for i in range(3):
        for j in range(n):
            resultLst[j][i] = ''.join(map(str, tmpLst[i][j]))

    print('#%d' %(test_case))
    for i in range(n):
        print(*(resultLst[i]))

