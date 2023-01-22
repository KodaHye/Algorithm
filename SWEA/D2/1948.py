# 날짜 계산기

T = int(input())

for test_case in range(1, T + 1):
    dayList = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    m1, d1, m2, d2 = map(int, input().split())
    days = 0

    if(m2 == m1):
        days = d2 - d1 + 1
    else:
        days = dayList[m1] - d1 + 1 + d2

    if(m2 > (m1 + 1)):
        for i in range(m1 + 1, m2):
            days += dayList[i]

    print("#%d %d" %(test_case, days))

