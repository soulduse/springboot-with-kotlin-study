# 11일차
----
## Data Rest

### REST API 설계
- 자원 resource: URI
- 행위 verb: HTTP Method
- 표현 representations: 리소스에 대한 표현(HTTP Message Body)

1. URI 설계
- URI는 URL을 포함하는 개념. 
```text
// URL이 웹상의 파일 위치를 표현하는 예
http://localhost:8080/api/book.pdf

// URI는 웹에 있는 자원의 이름과 위치를 식별
http://localhost:8080/api/book/1
```
> 따라서 URL은 URI의 하위개념. URL이 리소스를 가져오는 방법에 대한 위치라면 URI는 문자열을 식별하기 위한 표준

- URI는 명사를 사용해야 하며 동사를 피해야함.(무조건 적인건 아님)
- 동사를 표현하고 싶을땐 HTTP 메서드인 GET, POST, PUT, DELETE를 잘 활용해보자.

#### 복수형을 사용하라
- URI를 사용할 경우 컬렉션을 한번더 감싼 충접 형식으로 사용하는것이 좋다.
- 이유는 중첩하지 안혹 ㅏ볼 컬렉션을 반환하면 추후 수정시나 확장할때 번거로울 수 있다.
(웹도 고치고, 받아서 사용하는 클라도 고치고... 생각만해도 끔찍)

`안좋은 예)`
```json
{
  "book": {
    "name": "Example",
    "totalPage": 300
  }
}
```
- 위와 같이 작성했는데 복수의 형태로 변경해야된다면? 아래와 같이 변경하면 되겠지만 이 값을 받아서 사용하는 클라이언트에서도 수정이 불가피하다. 
```json
{
  "books": [
    {
      "name": "Example1",
      "totalPage": 300
    },
    {
      "name": "Example2",
      "totalPage": 350
    }
  ] 
}
```
- 여기서 또 문제가 있다. books 배열 객체와, stores 배열 객체도 추가하고싶다면 어떻게 될까?

```json
{
  "_embedded": [
    {
     "books": [
        {
          "name": "Example1",
          "totalPage": 300
        },
        {
          "name": "Example2",
          "totalPage": 350
        }
      ]
    },
    {
      "stores": [ ]
    }
  ] 
}
```
- 마찬가지로 또 서버 + 클라이언트 모두 작업을 해줘야 한다.
### 결론 : 처음부터 book, store같은 단일 값을 사용하는 것보단 books, stores를 사용하고, 이걸 한번 더 감싼 중첩 형식으로 사용하여 유연하게 대처하자.
추천 형식)
```json
{
  "_embedded": [
    {
     "books": [ ]
    },
    {
      "stores": [ ]
    }
  ] 
}
```
- 위와 같이 구성하면 새로운 값이 추가되어도 클라이언트 입장에서 추가되는 값은 안받으면 그만이니 큰 문제가 되진 않는다.

