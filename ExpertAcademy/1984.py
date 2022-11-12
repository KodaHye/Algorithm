# 중간 평균값 구하기
# 10개의 수를 입력 받아, 최대 수와 최소 수를 제외한 나머지 평균값을 출력하는 프로그램

T = int(input())

for test_case in range(1, T + 1):
    numList = map(int, input().split())
    numList = sorted(numList)

    print("#%d %d" %(test_case, round((sum(numList) - (numList[0] + numList[-1])) / (len(numList) - 2))))