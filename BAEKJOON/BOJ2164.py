# 카드2
import sys
from collections import deque

queue = deque()
n = int(sys.stdin.readline().strip())

for i in range(n):
    queue.append(i + 1)

while(len(queue) > 1):
    queue.popleft()
    queue.append(queue[0])
    queue.popleft()

print(queue[0])