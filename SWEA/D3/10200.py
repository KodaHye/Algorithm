# 구독자 전쟁

T = int(input())
for test_case in range(1, T + 1) :
    n, m, k = map(int, input().split())
    minPeople, maxPeople = 0, 0

    if(m + k <= n):
        maxPeople = min(m, k)
        minPeople = 0
    else:
        maxPeople = min(m, k)
        minPeople = max(m, k) + min(m, k) - n

    print("#%d %d %d" %(test_case, maxPeople, minPeople))
