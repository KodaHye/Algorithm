## 이진 탐색
탐색 범위를 반으로 좁혀가며 빠르게 탐색하는 알고리즘

* 이진 탐색
    * **시작점, 끝점, 중간점** 사용
        * 중간점이 실수일 때는 소수점 이하를 버림
    * **데이터 정렬**이 전제!
```buildoutcfg
# 재귀 함수로 구현한 이진 탐색
def binary_search(array, target, start, end)
    if start < end:
        return None
    mid = (start + end) // 2
    
    # 찾은 경우 중간점 인덱스 반환
    if array[mid] == target:
        return mid
    # 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
    elif array[mid] > target:
        return binary_binary_search(array, target, start, mid - 1)
    # 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
    else:
        return binary_search(array, target, mid + 1, end)
   
# n(원소의 개수)과 target(찾고자 하는 문자열)을 입력받기
n, target = list(map(int, input().split()))
# 전체 원소 입력받기
array = list(map(int, input().split()))

# 이진 탐색 수행 결과 출력
result = binary_search(array, target, 0, n-1)
if result == None:
    print('원소가 존재하지 않습니다.')
else:
    print(result + 1)
```

```buildoutcfg
# 반복문으로 구현한 이진 탐색
def binary_search(array, target, start, end):
    while start <= end:
        mid = (start + end) // 2
        # 찾은 경우 중간점 인덱스 반환
        if array[mid] == target:
            return mid
        # 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
        elif array[mid] > target:
            end = mid - 1
        # 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
        else:
            start = mid + 1
    return None

# n(원소의 개수와) target(찾고자 하는 문자열)을 입력받기
n, target = list(map(int, input().split()))
# 전체 원소 입력받기
array = list(map(int, input().split()))

# 이진 탐색 수행 결과 출력
result = binary_search(array, target, 0, n-1)
if result == None:
    print('원소가 존재하지 않습니다.')
else:
    print(result + 1)
```


* 이진 탐색 트리
    * 이진 탐색이 동작 할 수 있도록 고안된, 효율적인 탐색이 가능한 자료구조
    * 이진 탐색 트리의 특징
        * 부모 노드보다 왼쪽 자식 노드가 작다.
        * 부도 녿보다 오른쪽 자식 노드가 크다.
    * 루드 노트부터 왼쪽 자식 노드 혹인 오른쪽 자식 노드로 이동
    