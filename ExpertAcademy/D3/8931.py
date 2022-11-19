# 제로

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    lst = []

    for _ in range(n):
        k = int(input())
        if(k != 0):
            lst.append(k)
        else:
            lst.pop()
    print('#%d %d' %(test_case, sum(lst)))