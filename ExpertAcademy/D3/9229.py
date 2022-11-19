# 한빈이와 Spot Mart

T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())
    lst = list(map(int, input().split()))

    result = -1

    for i in range(n):
        for j in range(i + 1, n):
            if(lst[i] + lst[j] > result and lst[i] + lst[j] <= m):
                result = lst[i] + lst[j]


    print('#%d %d' %(test_case, result))