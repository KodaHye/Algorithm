# 정수를 저장하는 스택을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램

stack = []
N = int(input())
for _ in range(N):
    word = input().split()
    order = word[0]
    # orderNum = word[1]

    if(order == 'push'):
        stack.append(word[1])
    elif(order == 'pop'):
        if(len(stack) == 0):
            print(-1)
        else:
           print(stack[-1])
           stack.remove(stack[len(stack)])
    elif(order == 'size'):
        print(len(stack))
    elif(order == 'empty'):
        if(len(stack) == 0):
          print("1")
        else:
          print("0")
    elif(order == 'top'):
        if(len(stack) == 0):
            print(-1)
        else:
            print(len(stack))
            print(stack[-1])
