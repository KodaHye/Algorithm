# VPS

inputNum = int(input())

for i in range(inputNum):
    stack = []
    vpsSample = list(input())
    for i in range(0, len(vpsSample)):
        if(vpsSample[i] == '('):
            stack.append(vpsSample[i])
        else:
            stack.append(vpsSample[i])
            if(''.join(stack[-2:]) == '()'):
                stack.pop()
                stack.pop()
    if(len(stack) == 0):
        print("YES")
    else:
        print("NO")
