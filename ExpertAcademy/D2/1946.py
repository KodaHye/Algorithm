# 간단한 압축 풀기

# 너비가 10인 여러줄의 문자열
# 마지막 줄을 제외하고 인 공간 없이 채워져 있음

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    lst = []
    for _ in range(n):
        alp, num = input().split()
        lst += list(alp * int(num))

    print("#%d" %(test_case))
    for i in range(len(lst)):
        print(lst[i], end='')
        if(i % 10 == 9):
            print()
    print()
