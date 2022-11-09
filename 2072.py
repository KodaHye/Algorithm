# 10개의 수를 입력받아, 그 중에서 홀수만 더한 값을 출력하는 프로그램

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    input_list = list(map(int, input().split()))
    result_list = [input_list[i] for i in range(len(input_list)) if input_list[i] % 2 == 1]
    print("#%d %d" %(test_case, sum(result_list)))
