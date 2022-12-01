# 럭키 스트레이트
import sys
n = list(map(int, sys.stdin.readline().strip()))
# n = list(map(int, input()))

count = len(n)

if(sum(n[:count // 2]) == sum(n[count //2:])):
    print("LUCKY")
else:
    print("READY")