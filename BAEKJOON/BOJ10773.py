# stack에서는 append와 pop을 사용한다!

inputNum = int(input())
stack = []
for i in range(inputNum):
    inputStack = int(input())
    if(inputStack != 0):
        stack.append(inputStack)
    else:
        stack.pop()

print(sum(stack))