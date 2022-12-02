# 무지의 먹방 라이브

# 시간이 적게 걸리는 음식부터 확인하는 탐욕적 접근방식으로 해결
# 우선순위 큐를 이용하여 구현

def solution(food_times, k):
    answer = 0
    idx = 0

    while(k > 0):
        if(food_times[idx % 3] != 0):
            food_times[idx % 3] -= 1
            answer += 1
            k -= 1
            idx += 1
        else:
            idx += 1
        # if(answer == k):
        #     break
    if(''.join(map(str, food_times)) == '0' * len(food_times)):
        return -1
    else:
        return idx % 3 + 1

print(solution([3, 1, 2], 5))