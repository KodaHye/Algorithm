# 홀수일까 짝수일까

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    result = 'Odd'
    if(n % 2 == 0):
        result = 'Even'

    print('#%d %s' %(test_case, result))
