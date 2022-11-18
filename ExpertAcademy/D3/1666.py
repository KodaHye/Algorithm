# 팔씨름

T = int(input())

for test_case in range(1, T + 1):
    result = 'NO'
    s = input()

    countWin = s.count('o')

    if(countWin + (15 -  len(s)) >= 8) :
        result = 'YES'

    print('#%d' %(test_case), end = ' ')
    print(result)