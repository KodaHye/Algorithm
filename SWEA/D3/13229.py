# 일요일

T = int(input())

for test_case in range(1, T + 1):
    n = input()
    days = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"]
    result = 7

    for day in days:
        if(n == day):
            result = result - days.index(day)
            break

    print('#%d %d' %(test_case, result))