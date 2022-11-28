## 그래프 이론
코딩 테스트에서 자주 등장하는 기타 그래프 이론 공부하기

> **코딩테스트**에서 '서로 다른 개체가 연결되어 있다'<br>
> → **그래프 알고리즘**

* 그래프 구현 방법(메모리, 속도 측면에서 따지고 판단하기!)
    * 인접 행렬: 2차원 배열을 사용하는 방식
    * 인접 리스트: 리스트를 사용하는 방식


* 서로소 집합(`union-find`자료구조)
    * 공통 원소가 없는 집합
        * 서로소 관계: {1, 2}, {3, 4}
    * 서로소 부분 집합들로 나누어진 원소들의 데이터를 처리하기 위한 자료구조
    * 서로소 집합 자료구조 연산
        * `union`(합집합)
            * 2개의 원소가 포함된 집합을 하나의 집합으로 하비는 연산
        * `find`(찾기)
            * 특정한 원소가 속한 집합이 어떤 집합인지 알려주는 연산
    * 서로소 관계인지 확인할 수 있다 == 각 집합이 어떤 원소를 공통으로 가지고 잇는지 확인할 수 있다
    * **트리 자료구조**를 이용하여 집합 표현
    * '연결성'을 집합의 형태를 쉽게 파악할 수 있음
    * `union` 연산을 효과적으로 수행하기 위해 '부모 테이블'을 항상 가지고 있어야 함
    * 루트를 찾기 위해 재귀적으로 부모를 거슬러 올라가야 됨      

        1. `union`(합집합) 연산을 확인하여, 서로 연결된 두 노드 A, B를 확인한다.
           Ⅰ. A와 B의 루트노드 A', B;를 찾는다.
           Ⅱ. A'를 B'의 부모 노드로 설정한다.(B'가 A'를 가리키도록 한다.)

        2. 모든 `union`(합집합) 연산을 처리할 때가지 앞의 과정을 반복한다.
    

```buildoutcfg
# 기본적인 서로소 집합 알고리즘 소스코드
# 특정 원소가 속한 집합을 찾기
def find_parent(parent, x):
    # 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if parent[x] != x:
        return find_parent(parent, parent[x])
    return x

# 두 원소가 속한 집합을 합치기
def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

# 노드의 개수와 간선(union 연산)의 개수 입력받기
v, e = map(int, input().split())
parent = [0] * (v + 1) # 부모 테이블 초기화

# 부모 테이블상에서, 부모를 자기 자신으로 초기화
for i in range(1, v + 1):
    parent[i] = i
   
# union 연산을 각각 수행
for i in range(e):
    a, b = map(int, input().split())
    union_parent(parent, a, b)

# 각 원소가 속한 집합 출력
print('각 원소가 속한 집합: ', end = '')
for i in range(1, v + 1):
    print(find_parent(parent, i), end = ' ')

print()

# 부모 테이블 내용 출력 
print('부모 테이블: ', end = '')
for i in range(1, v + 1):
    print(parent[i], end = ' ')
```

* 기본적인 구현 방법의 문제점
    * 답을 구할 수는 있지만, `find`함수가 비효율적으로 동작함
        * 최악의 경우 시간 복잡도: O(V)
    * 경로 압축 기법을 적용하여 시간 복잡도 개선
        * `find`함수를 재귀적으로 호출한 뒤에 부모 테이블 값을 갱신
```buildoutcfg
# 경로 압축 기법 소스코드
def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]
```
* 함수를 위와 같이 수정하면 각 노드에 대해 find 함수를 호출한 이후에, 해당 노드의 루트 노드가 바로 부모 노드가 됨


```buildoutcfg
# 개선된 서로소 집합 알고리즘 소스코드
def find_parent(parent, x):
    # 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

# 두 원소가 속한 집합을 합치기
def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b 
    
# 노드의 개수와 간선(union 연산)의 개수 입력받기
v, e = map(int, input().split())
parent = [0] * (v + 1) # 부모 테이블 초기화

# 부모 테이블상에서, 부모를 자기 자신으로 초기화
for i in range(1, v + 1):
    parent[i] = i

# union 연산을 각각 수행
for i in range(e):
    a, b = map(int, input().split())
    union_parent(parent, a, b)

# 각 원소가 속한 집합 출력
print('각 원소가 속한 집합: ', end = '')
for i in range(1, v + 1):
    print(find_parent(parent, i), end =' ')
   
print()

# 부모 테이블 내용 출력
print('부모 테이블: ', end = '')
for i in range(1, v + 1):
    print(parent[i], end = ' ')
```

* 서로소 집합을 활용한 사이클 판별
    * 그래프에 포함되어 있는 간선의 수가 E개일 때 모든 간선을 하나씩 확인하며, 매 간선에 대해 `union`및 `find` 함수를 호출하는 방식으로 동작
    * 간선에 방향성이 없는 무향 그래프에서만 적용 가능
    

    1. 각 간선을 확인하며 두 노드의 루트 노드를 확인한다.
       1. 루트 노드가 서로 다르다면 두 노드에 대하여 `union` 연산을 수행한다.
       2. 루트 노드가 서로 같다면 사이클이 발생한 것이다.

    2. 그래프에 포함되어 있는 모든 간선에 대하여 위의 과정을 반복한다.
```buildoutcfg
# 서로소 집합을 활용한 사이클 판별 소스코드
# 특정 원소가 속한 집합을 찾기

def find_parent(parent, x):
    # 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

# 두 원소가 속한 집합 합치기
def union_parent(parent, a, v):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b
    
# 노드의 개수와 간선(union 연산)의 개수 입력받기
v, e = map(int, inpu().split())
parent = [0] * (v + 1) # 부모 테이블 초기화

# 부모 테이블상에서, 부모를 자기 자신으로 초기화
for i in range(1, v + 1):
    parent[i] = i

cycle = False # 사이클 발생 여부

for i in range(e):
    a, b = map(int, input().split())
    # 사이클이 발생한 경우 종료
    if find_parent(parent, a) == find_parent(parent, b):
        cycle = True
        break
    # 사이클이 발생하지 않았따면 합집합(union) 수행
    else:
        union_parent(parent, a, b)
    if cycle:
        print('사이클이 발생했습니다.')
    else:
        print('사이클이 발생하지 않았습니다.')
```

* 신장트리
    * 하나의 그래프가 있을 때 **모든 노드를 포함**하면서 **사이클이 존재하지 않는 부분 그래프**


* 크루스칼 알고리즘
    * 최소한의 비용으로 신장 트리를 찾을 경우 사용
        * 그리디 알고리즘으로 분류
        * 모든 간선에 대하여 정렬을 수행한 뒤에 가장 거리가 짧은 간선부터 집합에 포함
        * 사이클을 발생시킬 수 있는 간선의 경우, 집합에 포함시키지 않음
    * 대표적인 최소 신장 트리 알고리즘
    * 거리가 가장 짧은 간선부터 차례대로 집합에 추가하면 됨
        * 다만, 사이클을 발생시키는 간선은 제외하고 연결
    * 최소 신장 트리에 포함되어 있는 간선의 비용만 모두 더하면, 그 값이 최종 비용

```buildoutcfg
# 크루스칼 알고리즘 소스코드
# 특정 원소가 속한 집합을 찾기
def find_parent(parent, x):
    # 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]
    
# 두 원소가 속한 집ㅎ방르 합치기
def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b
    
# 노드의 개수와 간선(union 연산)의 개수 입력받기
v, c = map(int, input().split())
parent = [0] * (v + 1) # 부모 테이블 추기화

# 모든 간선을 담을 리스트와 최종 비용을 담을 변수
edges = []
result = 0

# 부모 테이블 상에서, 부모를 자기 자신으로 초기화
for i in range(1, v + 1):
    parent[i] = i

# 모든 간선에 대한 정보를 입력받기
for _ in range(e):
    a, b, cost = map(int, input().split())
    # 비용순으로 정렬하기 위해 튜플의 첫 번째 원소를 비용으로 설정
    edges.append((cost, a, b))
    
# 간선을 비용순으로 정렬
edges.sort()

# 간선을 하나씩 확인하며
for edge in edges:
    cost, a, b = edge
    # 사이클이 발생하지 않는 경우에만 집합에 포함
    if find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        result += cost
    print(result)
```

* 위상정렬
    * 방향 그래프의 모든 노드를 '방향성에 거스르지 않도록 순서대로 나열하는 것'
    * '선수 과목을 고려한 학습 순서 설정'
        * 그래프 상에서 선후 관계가 있으면, 위상 정렬을 수행하여 모든 선후 관계를 지키는 전체 순서를 계산할 수 있음
    * 모든 원소를 방문하기 전에 큐가 빈다면 사이클이 존재한다고 판단


    1. 진입 차수가 0인 노드를 큐에 넣는다.
    2. 큐가 빌 때까지 다음의 과정 반복
        1. 큐에서 원소를 꺼내 해당 노드에서 출발하는 간선을 그래프에서 제거
        2. 새롭게 진입차수가 0이 된 노드를 큐에 넣음
    
