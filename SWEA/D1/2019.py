# 1부터 주어진 횟수까지 2를 곱한 값을 출력하시오

inputNum = int(input())
outputNum = 1
for i in range(0, inputNum + 1):
    print(outputNum, end =" ")
    outputNum = outputNum * 2

