# 2016년 요일 맞추기

T = int(input())

for test_case in range(1, T + 1):
    m, d = map(int, input().split())
    day = [0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    for i in range(1, m):
        d += day[i]

    print('#%d %d' %(test_case, ((d % 7) + 3) % 7))