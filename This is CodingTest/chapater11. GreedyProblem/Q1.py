# 모험가 길드
# 풀이보고 수정함
n = int(input())
items = list(map(int, input().split()))
group = 0 # 그룹의 수
count = 0 # 그룹에 포함된 모험가의 수

items.sort()

for item in items:
    count += 1
    if(count >= item):
        group += 1
        count = 0
        
print(group)