# 이것이 코딩테스트다
# p.115

locate = input()
locateCol = locate[0]
locateRow = int(locate[1])
result = 0

if(locateRow > 1 and locateRow < 8 and ord(locateCol) > ord('a') and ord(locateCol) < ord('h')):
    result = 8
elif(locateRow == (1 or 7) and ord(locateCol) == (ord('a') or ord('h'))):
    result = 2
else:
    result = 4

print(result)