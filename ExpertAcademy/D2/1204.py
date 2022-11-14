# 최빈수 구하기
# 문제 잘 읽기!
# 라이브러리 함수를 사용했을 때의 문제점은?

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    score = [0] * 101
    max = 0
    result = 0
    studentScore = list(map(int, input().split()))

    for i in studentScore:
        score[i] += 1

    for j in range(len(score)):
        if(score[j] >= max):
            max = score[j]
            result = j

    print("#%d %d" %(n, result))