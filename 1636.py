# 가위바위보 연산
A, B = map(int, input().split())
if(A == 1):
    if(B == 2):
        print("B")
    else:
        print("A")
elif(A == 2):
    if(B == 1):
        print("A")
    else:
        print("B")
else:
    if(B == 1):
        print("B")
    else:
        print("A")