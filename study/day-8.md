# 8일차
----

## 커뮤니티 게시판 구현

### 순서 
1. 프로젝트 의존성 구성
2. 스프링 부트 웹 스타터 살펴보기
3. 도메인 매핑
4. 도메인 테스트
5. CommandLineRunner를 사용하여 DB 에 데이터 넣기
6. 게시글 리스트 만들기
7. 타임리프 날짜 포멧 라이브러리 추가
8. 페이징 처리
9. 작성 폼 추가

## 궁금했던 것 과 알게된 것
1. 의존성 구성은 간한단 gradle 정의 하는 것이었고, 
2. 스프링 부트 스타터에는 다양한 것들이 내제 되어있는걸 알 수 있었다.
3. 도메인 매핑은 아래와 같이 매핑하는 작업인데, @GetMapping에 여러개의 리스트를 한번에 받아서 처리 할 수 있어서 유용하게 사용할 수 있을것 같다.
```kotlin
@GetMapping("", "/") // 매핑경로 다중화
fun board(
    // RequestParam 어노테이션 사용하여 idx 값을 받음 값이 없으면 기본값 0, 0으로 board를 조회하면 null이 리턴됨.
    @RequestParam(value = "idx", defaultValue = "0")
    idx: Long,
    model: Model
): String {
    model.addAttribute("board", boardService.findBoardByIdx(idx))
    return "/board/form"
}

@GetMapping("/list")
fun list(
    // PageableDefault를 사용하면 파라미터인 size, sort, direction 등을 사용하여 페이징 처리에 대한 규약을 정의 가능
    @PageableDefault
    pageable: Pageable,
    model: Model
): String {
    model.addAttribute("boards", boardService.findBoards(pageable))
    return "/board/list" // src/resource/template를 기준으로 데이터 바인딩할 타깃뷰 경로 지정
}
```
그리고 return String 값이 // src/resource/template를 기본적으로 바라보고 바인딩하는것을 알 수 있었다.
5. CommandLineRunner 대량의 데이터를 임의로 주입할때 사용하면 유용한 방식을 알게 되었다.
6 ~ 9번은 HTML + 타임리프라서 크게 어려운건 없었다. 깊이있게 파고드는것 보다는 만들면서 필요한 부분을 그때그때 학습하도록 해야겠다. 
