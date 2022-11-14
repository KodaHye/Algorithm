# 큰 수의 법칙

n, m, k = map(int, input().split())

inputNum = list(map(int, input().split()))
inputNum = sorted(inputNum, reverse=True)

result = (inputNum[0] * k + inputNum[1]) * (m // (k + 1)) + inputNum[0] * (m % (k + 1))

print(result)