## 다이나믹 프로그래밍(동적계획법)
한 번 계산한 문제는 다시 계산하지 않도록 하는 알고리즘


* 컴퓨터 연산 속도의 한계와 메모리 공간의 한계
    * **속도**와 **메모리 공간**을 **최대한으로 활용**할 수 있는 **효율적인 알고리즘**을 작성해야 함

* 다이나믹 프로그래밍(동적 계획법)
    * 큰 문제를 작게 나누고, 같은 문제라면 한 번씩만 풀어 문제를 효율적으로 해결하는 알고리즘
    * 메모리 공간을 약간 사용하여 속도를 비약적으로 증가할 수 있음
    
```buildoutcfg
# 피보나치 함수(Fibonacci Function)를 재귀 함수로 구현
def fibo(x):
    if x == 1 or x == 2:
        return 1
    return fibo(x-1) + fibo(x-2)

print(fibo(4))
```

* 다이나믹 프로그램을 사용할 수 있는 조건
    1. 큰 문제를 작은 문제로 나눌 수 있다.
    2. 작은 문제에서 구한 정답은 그것을 포함하는 큰 문제에서도 동일하다.
    
* 메모이제이션
    * 다이나믹 프로그래밍을 구현하는 방법 중 하나
    * 한 번 구한 결과를 메모리 공간에 저장하고 같은 식을 다시 호출
    * 캐싱이라고도 함
```buildoutcfg
# 피보나치 수열 소스코드(재귀적)
# 한 번 계산된 결과를 메모이제이션하기 위한 리스트 초기하
d = [0] * 100

# 피보나치 함수를 재귀함수로 구현(탑다운 다이나믹 프로그래밍)
def fibo(x):
    # 종료조건(1 혹은 2일 때 1을 반환
    if x == 1 or x == 2:
        reutrn 1
    # 이미 계산한 적 있는 문제라면 그대로 반환
    if d[x] != 0:
        return d[x]
    # 아직 계산하지 않은 문제라면 점화식에 따라서 피보나치 결과 반환
    d[x] = fibo(x-1) + fibo(x-2)
    return d[x]

print(fibo(99))
```

```buildoutcfg
# 피보나치 수열 소스코드(반복적)
# 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
d = [0] * 100

# 첫 번째 피보나치 수와 두 번쨰 피보나치 수는 1
d[1] = 1
d[2] = 1
n = 99

# 피보나치 함수를 반복문으로 구현(보텀업 다이나믹 프로그래밍)
for i in range(3, n + 1):
    d[i] = d[i-1] + d[i-2]

print(d[n])
```