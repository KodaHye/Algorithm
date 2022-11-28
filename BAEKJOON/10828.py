import sys

# 정수를 저장하는 스택을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램
n = int(sys.stdin.readline().strip())

stack = []

for _ in range(n):
    word = sys.stdin.readline().strip()
    command = word.split()[0]

    if(command == 'push'):
        stack.append(word.split()[1])
    elif(command == 'top'):
        if(len(stack) == 0):
            print(-1)
        else:
            print(stack[-1])
    elif(command == 'size'):
        print(len(stack))
    elif(command == 'empty'):
        if(len(stack) == 0):
            print(1)
        else:
            print(0)
    elif(command == 'pop'):
        if(len(stack) == 0):
            print(-1)
        else:
            print(stack[-1])
            stack.pop()
