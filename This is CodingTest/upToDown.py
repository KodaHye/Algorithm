# 이것이 코딩테스트다
# p.178

inputNum = int(input())
array = []

for i in range(inputNum):
    array.append(int(input()))

array = sorted(array, reverse=True)

for i in range(len(array)):
    print(array[i], end = ' ')