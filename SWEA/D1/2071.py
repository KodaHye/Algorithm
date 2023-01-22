# 10개의 수를 입력받아 평균값을 출력하는 프로그램

T = int(input())
for test_case in range(1, T + 1):
    inputList = list(map(int, input().split()))
    print("#%d %d" % (test_case, round(sum(inputList) / 10)))