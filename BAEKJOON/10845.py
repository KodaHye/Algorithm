# 큐
import sys
from collections import deque

n = int(sys.stdin.readline().strip())
queue = deque()

for _ in range(n):
    word = sys.stdin.readline().strip()
    command = word.split()[0]
    if(command == 'push'):
        queue.append(word.split()[1])
    elif(command == 'pop'):
        if(len(queue) == 0):
            print(-1)
        else:
            print(queue[0])
            queue.popleft()
    elif(command == 'size'):
        print(len(queue))
    elif(command == 'empty'):
        if(len(queue) == 0):
            print(1)
        else:
            print(0)
    elif(command == 'front'):
        if(len(queue) == 0):
            print(-1)
        else:
            print(queue[0])
    elif(command == 'back'):
        if(len(queue) == 0):
            print(-1)
        else:
            print(queue[-1])