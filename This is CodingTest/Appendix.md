#### 주요 라이브러리의 문법과 유의점
* `itertools`
    * 파이썬에서 반복되는 형태의 데이터를 처리하는 기능을 제공하는 라이브러리
    * 순열과 조합 라이브러리 제공
* `heapq`
    * 힙 기능을 제공하는 라이브러리
    * 우선순위 큐 기능을 구현하기 위해 사용
* `bisect`
    * 이진 탐색 기능을 제공하는 라이브러리
* `collections`
    * 덱, 카운터 등의 유용한 자료구조를 포함하고 있는 라이브러리
* `math`
    * 필수적인 수학적 기능을 제공하는 라이브러리


#### itertools
* 반복되는 데이터를 처리하는 기능을 포함하는 기능을 포함하는 라이브러리
* `permutations`
    * `iterable` 객체에 r개의 데이터를 뽑아 일렬로 나열하는 모든 경우(순열)를 계산
```buildoutcfg
from itertools import permutations

data = ['A', 'B', 'C'] # 데이터 준비
result = list(permutations(data, 3)) # 모든 순열 구하기

print(result)

>> [('A', 'B', 'C), ('A', 'C', 'B'), ('B', 'A', 'C'), ('B', 'C', 'A'), ('C', 'A', 'B'), ('C', 'B', 'A')]
```


* `combinations`
    * 리스트와 같은 `iterablbe`객체에서 r개의 데이터를 뽑아 순서를 고려하지 않고 나열하는 경우(조합)
```buildoutcfg
from itertools import combinations

data = ['A', 'B', 'C'] # 데이터 준비
result = list(combinations(data, 2)) # 2개를 뽑는 모든 조합 구하기

print(result)
>> [('A', 'B'), ('A', 'C'), ('B', 'C')]
```


* `product`
    * `iterable` 객체에서 r개의 데이터를 뽑아 일렬로 나열하는 모든 경우(순열)
    * 중복허용
```buildoutcfg
# 중복을 포함하여 2개를 뽑아 나열하는 모든 경우
from itertools import product

data = ['A', 'B', 'C'] # 데이터 준비
result = list(product(data, repeat = 2)) # 2개를 뽑는 모든 순열 구하기(중복허용)

print(result)
```


* `combinations_with_replacement`
    * `iteralbe` 객체에서 r개의 데이터를 뽑아 순서를 고려하지 않고 나열하는 모든 경우(중복허용)
```buildoutcfg
from itertools import combinations_with_replacment

data = ['A', 'B', 'C'] # 데이터 준비
result = list(combinations_with_replacement(data, 2)) # 2개를 뽑는 모든 조합 구하기(중복 허용)
print(result)
>> [('A', 'A'), ('A', 'B'), ('A', 'C'), ('B', 'B'), ('B', 'C'), ('C', 'C')]
```

