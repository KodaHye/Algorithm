# 두 전구

T = int(input())

for test_case in range(1, T + 1):
    a, b, c, d = map(int, input().split())
    result = 0

    if(c < b):
        if(b < d):
            result = b - c
        else:
            result = d - c
    print('#%d %d' %(test_case, result))