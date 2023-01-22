# 패턴 마디의 길이

n = int(input())

for test_case in range(n):
    words = input()
    for i in range(1, len(words)):
        word = words[:len(words) - i]

        print(">>", words[:len(words) // len(word) * len(word)])
        # if(word == words[:len(words) // len(word) * len(word)]):
        #     break
    print("#%d %d" %(test_case + 1, len(words) / len(word)))