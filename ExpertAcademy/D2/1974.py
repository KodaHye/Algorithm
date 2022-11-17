# 스도쿠 검증

T = int(input())

for test_case in range(1, T + 1):
    lst = []
    lstTmp = []
    result = 1

    for _ in range(9):
        lst.append(list(map(int, input().split())))

    lstTmp = list(zip(*lst))
    for i in range(9):
        if(''.join(map(str, sorted(lst[i]))) != '123456789'):
            result = 0
            break
        elif(''.join(map(str, sorted(lstTmp[i]))) != '123456789'):
            result = 0
            break

    for i in range(0, 9, 3):

        for j in range(0, 9, 3):
            puzzleSum = 0
            puzzleSum += sum(lst[i][j:j+3])
            puzzleSum += sum(lst[i+1][j:j+3])
            puzzleSum += sum(lst[i+2][j:j+3])
            if(puzzleSum != 45):
                result = 0
                break
    print("#%d %d" %(test_case, result))
