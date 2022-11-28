# 정사각형 판정

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    count = 0
    sqrLength = 0
    result = 'yes'
    lst = []

    for _ in range(n):
        lst.append(input())

    for i in range(n):
        for j in range(len(lst[i])):
            if(lst[i][j] == '#'):
                sqrLength = lst[i].count('#')

            if(''.join(lst[i][j:j + sqrLength + 1]) != '#' * sqrLength):
                print(sqrLength)
                result = 'no'
                break

            for k in range(sqrLength):
                print('sqrLength: ', sqrLength)
                print(''.join(lst[i+k][j:j + sqrLength + 1]))
                if(''.join(lst[i+k][j:j + sqrLength + 1]) != '#' * sqrLength):
                    print(sqrLength)
                    result = 'no'
                    break
            break
    print('#%d' %(test_case), end = ' ')
    print(result)

    #             sqrI, sqrJ = i, j
    #             break
    #
    # # for i in range(n):
    #
    # print(sqrLength, sqrI, sqrJ)


        # for j in range(len(lst[i])):

    #             count += 1
    # print(count)
    #
    # for i in range(n):
    #     for j in range(n):
    #         if(lst[i][j] == '#'):
    #             str = ''.join(lst[i][j:j + int(count ** (1 / 2))]) == '#' * int(count ** (1 / 2))