# 정사각형 판정

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    count = 0
    lst = []

    for _ in range(n):
        lst.append(input())

    for i in range(n):
        for j in range(len(lst[i])):
            if(lst[i][j] == '#'):
                count += 1
    print(count)

    for i in range(n):
        for j in range(n):
            if(lst[i][j] == '#'):
                str = ''.join(lst[i][j:j + int(count ** (1 / 2))]) == '#' * int(count ** (1 / 2))