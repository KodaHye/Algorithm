# 숫자 조작

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    max, min = n, n

    for i in range(len(list(str(n)))):
        for j in range(i + 1, len(list(str(n)))):
            nCopy = list(str(n))
            tmp = 0
            if(n != 0):
                nCopy[i], nCopy[j] = nCopy[j], nCopy[i]
                tmp = int(''.join(nCopy))
                if(len(str(tmp)) != len(nCopy)):
                    continue
            if(tmp <= min):
                min = tmp
            elif(tmp > max):
                max = tmp

    print('#%d %d %d' %(test_case, min, max))
