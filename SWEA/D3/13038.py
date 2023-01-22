# 교환학생

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    days = 0
    lectures = list(map(int, input().split()))

    if(n % sum(lectures) == 0):
        weekLec = n // sum(lectures)
        days = (weekLec - 1) * 7
        n = n % sum(lectures)
    else:
        weekLec = n // sum(lectures)
        days = weekLec * 7
        n = n % sum(lectures)

    while(n > 0):
        for i in range(len(lectures)):
            n -= lectures[i]

    print("#%d %d" %(test_case, days + 1))