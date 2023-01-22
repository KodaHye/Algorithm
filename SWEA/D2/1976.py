# 시각 덧셈

# 시 분으로 이루어진 시각을 2개 입력 받아, 더한 값을 시, 분으로 출력하는 프로그램을 작성

T = int(input())

for test_case in range(1, T + 1):
    h1, m1, h2, m2 = map(int,input().split())

    m = m1 + m2
    h = h1 + h2

    if(m >= 60):
        m %= 60
        h += 1

    if(h >= 13):
        h %= 12
        if(h == 0):
            h = 12
    print("#%d %d %d" %(test_case, h, m))