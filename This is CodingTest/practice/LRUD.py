# 이것이 코딩테스트다
# p.110

areaSize = int(input())
locateX = 1
locateY = 1

LRUD = list(map(str, input().split()))

for i in range(0, len(LRUD)):
    if(LRUD[i] == 'L'):
        if(locateX == 1):
            continue
        else:
            locateX -= 1
    elif(LRUD[i] == 'R'):
        if(locateX == areaSize):
            continue
        else:
            locateX += 1
    elif(LRUD[i] == 'U'):
        if(locateY == 1):
            continue
        else:
            locateY -= 1
    elif(LRUD[i] == 'D'):
        if(locateY == areaSize):
            continue
        else:
            locateY += 1

print("%d %d" % (locateY, locateX))