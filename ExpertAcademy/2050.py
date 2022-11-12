# 알파벳을 숫자로 변환

inputStr = input()

for alp in inputStr:
    print(ord(alp) - ord('A') + 1, end =' ')