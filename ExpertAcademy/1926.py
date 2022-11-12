# 간단한 369 게임

inputNum = int(input())

for i in range(1, inputNum + 1):
    i = str(i)
    clap = ''
    for j in i:
        if int(j) % 3 == 0 and int(j) != 0:
             clap += '-'
    if clap == '':
        print("%d" % (int(i)), end = ' ')
    else:
        print(clap, end = ' ')

# 다른 사람 풀이
# testcase = int(input())
# res = []
#
# for i in range(1, testcase + 1):
#     i = str(i)
#     ans = i.count('3') + i.count('6') + i.count('9')
#     if ans == 0:
#         res.append(i)
#     else:
#         ans = '-' * ans
#         res.append(ans)
# print(*res)