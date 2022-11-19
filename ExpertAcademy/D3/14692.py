# 통나무 자르기

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    result = ''

    if(n % 2 ==0):
        result = 'Alice'
    else:
        result = 'Bob'
    print('#%d' %(test_case), end = ' ')
    print(result)