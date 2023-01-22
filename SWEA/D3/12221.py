# 구구단2

T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())
    result = -1

    if(n < 10 and m < 10):
        result = n * m

    print('#%d %d' %(test_case, result))
