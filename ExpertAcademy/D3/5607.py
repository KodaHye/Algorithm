# 조합
# 시간 초과 뜸
# 페르마 소정리 쓰라고 함,,

import math
T = int(input())

for test_case in range(1, T + 1):
    n, r = map(int, input().split())

    result = math.factorial(n) // (math.factorial(r) * math.factorial(n-r))

    print('#%d %d' %(test_case, result % 1234567891))
