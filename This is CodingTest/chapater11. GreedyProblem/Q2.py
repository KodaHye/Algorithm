# 곱하기 혹은 더하기

n = input()
lst = []
for i in range(len(n)):
    lst.append(int(n[i]))

tmp = 0

for i in range(1, len(n)):
    if(i == 1):
        tmp = max(lst[i - 1] * lst[i], lst[i - 1] + lst[i])
    else:
       tmp = max(tmp * lst[i], tmp + lst[i])

print(tmp)