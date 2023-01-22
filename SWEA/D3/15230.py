# 알파벳 공부

T = int(input())

for test_case in range(1, T + 1):
    n = list(map(str, input()))

    for i in range(len(n)):
        if(ord(n[i]) - ord('a') + 1 == i + 1):
            if(i == len(n) - 1):
                print("#%d %d" %(test_case, i + 1))
            continue
        else:
            print("#%d %d" %(test_case, i))
            break