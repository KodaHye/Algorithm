# 연월일 달력

T = int(input())

for test_case in range(1, T + 1):
    n = input()
    days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if(int(n[4:6]) > 0 and int(n[4:6]) < 13 and days[int(n[4:6]) - 1] >= int(n[6:8])):
        print('#%d' %(test_case), end = ' ')
        print(str(n[0:4]) + '/' + str(n[4:6] + '/') + str(n[6:8]))
    else:
        print('#%d %d' %(test_case, -1))