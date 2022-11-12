# 아름이의 돌 던지기
# 아름이 포함 N명 돌 던지기
# N명의 사람들이 던진 돌이 떨어진 위치를 측정한 자료가 주어질 때, 가장 0에 가깝게 돌이 떨어진 위치와 0 사이의 거리 차이와 몇 명이 그렇게 돌을 던졌는지

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    dist = list(map(int, input().split()))

    for i in range(0, len(dist)):
        dist[i] = abs(dist[i])

    dist = sorted(dist)

    print("#%d %d %d" %(test_case, dist[0], dist.count(dist[0])))