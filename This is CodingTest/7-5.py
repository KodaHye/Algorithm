# 이것이 코딩테스트다
# 부품찾기
# p.198

n = int(input())
# storeItem = list(map(int, input().split()))
storeItem = set(map(int, input().split()))

m = int(input())
reqItem = list(map(int, input().split()))

for i in range(len(reqItem)):
    if(reqItem[i] in storeItem):
        print("yes", end =' ')
    else:
        print("no", end = ' ')