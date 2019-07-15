# 5일차
----

## @WebMvcTest
- MVC를 위한 테스트. 
- 웹에서 테스트 하기 힘든 컨트롤러를 테스트 하는 데 적합.
- 시큐리티, 필터까지 자동으로 테스트 가능, 수동으로 추가/삭제 가능

- @Controller, @ControllerAdvice, @JsonComponent, 
Filter, WebMvcConfigurer, HandlerMethodArgumentResolver만 로드됨. 
=> 따라서 @SpringBootTest 어노테이션보다 가볍게 테스트 가능!


# 궁금했던 것 과 알게된 것
- 컨트롤러 특화된 테스트는 @WebMvcTest를 사용하면 되겠다.
