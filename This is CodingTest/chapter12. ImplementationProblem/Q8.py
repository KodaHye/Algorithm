S = list(input())

tmpA = []
tmpB = []

for i in range(len(S)):
    if(ord(S[i]) >= 49 and ord(S[i]) <= 59):
        tmpB.append(S[i])
    else:
        tmpA.append(S[i])

tmpA.sort()
print(''.join(tmpA + tmpB))

