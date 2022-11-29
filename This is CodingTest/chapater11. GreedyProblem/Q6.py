# 무지의 먹방 라이브
# 먹방을 시작한 지 K초 후에 너트워크 장애로 인해 방송 중지
# 네트워크 정상화 후 다시 방송을 이어갈 때, 몇 번 음식부터 섭취해야되는지

# 음식을 모두 먹는데 필요한 시간이 담겨있는 배열: food_times
    # food_times의 길이: 1 이상 2,000 이하
    # food_times의 원소: 1 이상 1,000이하의 자연수
# 네크워크 장애가 발생한 시간: K초
    # k: 1 이상 2,000,000 이하의 자연수
# 더 섭취할 음식이 없으면 -1 반환

def solution(food_times, k):
    answer = 0

    idx = 0
    count = 0

    while(count <= sum(food_times)):
        for i in range(sum(food_times)):
            count += 1
            if(food_times[i%3] != 0):
                food_times[i % 3] -= 1
                idx = i % 3
            else:
                continue
        if(count == k):
            break
    if(''.join(map(str, food_times)) == '0' * len(food_times)):
        return -1
    return (idx + 1) % 3 + 1

print(solution([3, 1, 2], 5))