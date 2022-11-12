# 초심자의 회문 검사

T = int(input())
result = 0
for test_case in range(1, T + 1):
    inputWord = input()
    for i in range(len(inputWord) // 2):
        if(inputWord[i] == inputWord[-(i+1)]):
            result = 1
        else:
            result = 0
            break
    print("#%d %d" % (test_case, result))

