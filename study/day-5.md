# 5일차
----

## @WebMvcTest
- MVC를 위한 테스트. 
- 웹에서 테스트 하기 힘든 컨트롤러를 테스트 하는 데 적합.
- 시큐리티, 필터까지 자동으로 테스트 가능, 수동으로 추가/삭제 가능

- @Controller, @ControllerAdvice, @JsonComponent, 
Filter, WebMvcConfigurer, HandlerMethodArgumentResolver만 로드됨. 
=> 따라서 @SpringBootTest 어노테이션보다 가볍게 테스트 가능!

> Controller Test
```kotlin
@RunWith(SpringRunner::class)
@WebMvcTest(BookController::class)
class BookControllerTest {

	@Autowired
	private lateinit var mvc: MockMvc

	@MockBean
	private lateinit var bookService: BookService

	@Test
	fun `Book MVC Test`() {
		val book = Book(
				titleField = "Spring Boot Book",
				publishedAtField = LocalDateTime.now()
		)
		given(bookService.getBookList()).willReturn(arrayListOf(book)) // given으로 미리 데이터를 주고 더미값을 받을 수 있구나!
		mvc.perform(get("/books")) // '/books' 호출
				.andExpect(status().isOk) // HTTP 상탯값이 200인치 테스트
				.andExpect(view().name("book")) // 반환되는 뷰의 이름이 'book'인지 테스트
				.andExpect(model().attributeExists("bookList")) // 모델 프로퍼티중 'bookList'가 있는지 테스트
				.andExpect(model().attribute("bookList", contains(book))) // 'bookList'라는 프로퍼티에 book 객체가 담겨 있는지 테스트
	}
}
```


# 궁금했던 것 과 알게된 것
- 컨트롤러 특화된 테스트는 @WebMvcTest를 사용하면 되겠다.
- MVC 테스트는 MockMvc를 주입하여 HTTP 서버를 실행하지 않고 테스트 가능
- @Service는 @WebMvcTest의 적용대상이 아니다 > 그럼 실제 Service는 실행하지 않고, 임의의 인터페이스를 정의하여 목업하여 사용한다.

# 의문점
- 그럼 @WebMvcTest의 경우에 테스트를 통해 컨트롤러 테스트를 해서 얻는 이득은 무엇인가? 단순히 HTTP, 시큐리티, 필터 정도의 테스트인가?
- 실제 @Service를 사용하지 않기 때문에 기존에 @Controller에서 추가로 테스트용 함수를 추가로 만들어줘야 하는가?
