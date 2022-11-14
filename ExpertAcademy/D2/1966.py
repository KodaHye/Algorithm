# 숫자를 정렬하자
# 주어진 N 길이의 숫자열을 오름차순으로 정렬하여 출력

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    lst = list(map(int, input().split()))

    lst.sort()

    print("#%d" %(test_case), end =' ')
    print(' '.join(map(str, lst)))

