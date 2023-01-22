# 두문자어

T = int(input())

for test_case in range(1, T + 1):
    s = list(input().split())

    result = s[0][0].upper() + s[1][0].upper() + s[2][0].upper()

    print("#%d %d" %(test_case, result))