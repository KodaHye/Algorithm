# 무한 문자열

T = int(input())

for test_case in range(1, T + 1):
    s, t = input().split()
    result = 'no'

    if(len(s) == len(t)):
        if(s == t):
            result = 'yes'
    else:
        if(s * len(t) == t * len(s)):
            result = 'yes'

    print('#%d' %(test_case), end = ' ')
    print(result)
