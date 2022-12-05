# 구구단 1

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    result = 'No'
    for i in range(1, 10):
        for j in range(1, 10):
            if(i * j == n):
                result = 'Yes'
                break
    print('#%d' %(test_case), end = ' ')
    print(result)