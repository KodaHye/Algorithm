# 순열1

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    lst = list(map(int, input().split()))

    lst.sort()

    for i in range(n):
        if(lst[i] != i + 1):
            print("#%d No" %(test_case))
            break
        else:
            if(i + 1 == n):
                print("#%d Yes" % (test_case))
            continue