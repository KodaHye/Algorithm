# 이것이 코딩테스트다
# p.99

n, k = map(int, input().split())

count = 0

while(n != 1):
    if(n % k == 0):
        count += 1
        n /= k
    else:
        count += 1
        n -= 1
print(count)