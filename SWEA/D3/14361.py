# 숫자가 같은 배수

T = int(input())

for test_case in range(1, T + 1):
    result = ''
    num = int(input())
    numCount = len(str(num))
    i = 2
    while(True):
        mulNum = num * i
        numList = sorted(list(str(mulNum)))
        if(''.join(numList) == ''.join(sorted(list(str(num))))):
            result = 'possible'
            break
        if(len(numList) > numCount):
            result = 'impossible'
            break
        i += 1
    print("#%d" %(test_case), end =' ')
    print(result)
