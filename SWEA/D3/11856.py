# 반반

T = int(input())

for test_case in range(1, T + 1):
    S = set(input())

    if(len(S) == 2):
        result = 'Yes'
    else:
        result = 'No'
    print('#%d' %(test_case), end = ' ')
    print(result)
