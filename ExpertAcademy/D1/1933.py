# 간단한 N의 약수

inputNum = int(input())

for i in range(1, inputNum + 1):
    if(inputNum % i == 0):
        print(i, end= " ")