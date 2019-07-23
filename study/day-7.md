# 7일차
----

## @RestClientTest
- REST 관련 테스트를 도와주는 어노테이션
- REST 통신의 데이터 형으로 사용되는 Json 형식이 예상대로 응답을 반환하는지 테스트 가능

```kotlin
@RunWith(SpringRunner::class)
// 테스트 대상이 되는 빈을 주입
@RestClientTest(BookRestService::class)
class BookRestTest {

	// @Rule로 지정한 필드값은 @Before, @After 어노테이션에 상관없이 하나의 테스트 메서드가 끝날 때마다 정의한 값으로 초기화
	// 테스트에서 자체적으로 규칙을 정의하여 재사용할 때 유용
	@Rule
	@JvmField
	val thrown = ExpectedException.none()

	@Autowired
	private lateinit var bookRestService: BookRestService

	// MockRestServiceServer는 클라이언트와 서버 사이에 REST 테스트를 위한 객체
	// 목 객체와 같이 실제로 통신이 이루어지지는 않음.
	// 지정한 경로에 예상되는 반환값 혹은 에러를 반환하도록 명시할 수 있음.
	@Autowired
	private lateinit var server: MockRestServiceServer

	@Test
	fun `rest 테스트`() {
		this.server.expect(requestTo("/rest/test"))
				.andRespond(withSuccess(ClassPathResource("/test.json", javaClass), MediaType.APPLICATION_JSON))
		val book = this.bookRestService.getRestBook()
		assertThat(book?.title).isEqualTo("테스트")
	}

	@Test
	fun `rest error 테스트`() {
		this.server.expect(requestTo("/rest/test"))
				.andRespond(withUnauthorizedRequest())
		this.thrown.expect(HttpServerErrorException::class.java)
		this.bookRestService.getRestBook()
	}
}

```

## 궁금했던 것 과 알게된 것
- 굳이 더미값으로 왜 테스트를 할까 직접 쿼리해서 나온 결과값도 아닌데라고 생각이 들었다.
- 그런데 한번 테스트를 돌려보니 알게된사실 예상하는 json의 값과 결과 Entity의 구조가 일치하지 않으니 에러가 발생
    - 해당 테스트를 돌려보고나서 Entity를 수정하거나 예상 데이터를 고칠 수 있겠구나라는 생각이 들었다.
- 해당 파트 설명 마지막부에 "REST 요청 테스트가 성공하는 경우와 실패하는 경우 어떤 응답을 줄지 여러 테스트 코드를 미리 작성해보면 애플리케이션의 결함을 줄이는 데 도움이 될 것이다."와 같은 말이 있다. 잘 요약한 내용인것 같다.


## @JsonTest
- Json을 테스트하는 방법이 있다고 한다 알아보자.
- @JsonTest는 Json의 직렬화와 역직렬화를 수행하는 라이브러리인 Gson과 Jackson API의 테스트를 제공
- GsonTester, JacksonTester를 사용하여 테스트를 수행
- Json 테스트는 크게 두가지로 나뉜다. 
    1. 문자열로 나열된 Json > 데이터 객체로 변환하여 그 값을 테스트
    2. 객체 > 문자열 Json 변환 후 그 값을 테스트

```kotlin
@RunWith(SpringRunner::class)
@JsonTest
class BookJsonTest {

	@Autowired
	private lateinit var json: JacksonTester<Book> // JacksonTester라 Not bean으로 @Autowired를 할 수 없다고 빨간줄이 뜨는데 실행이 잘된다 이상하네

	@Test
	fun `json 테스트`() {
		val book = Book(title = "테스트")
		val content = "{\"title\":\"테스트\"}"
		assertThat(this.json.parseObject(content).title).isEqualTo(book.title)
		assertThat(this.json.parseObject(content).publishedAt).isNull()
		assertThat(this.json.write(book)).isEqualToJson("/test.json")
		assertThat(this.json.write(book)).hasJsonPathStringValue("title")
		assertThat(this.json.write(book)).extractingJsonPathStringValue("title").isEqualTo("테스트")
	}
}
```

## 궁금했던 것 과 알게된 것
- 안드로이드 할 때 Gson은 정말 많이 썼었는데 이걸 테스트하는 걸 제공하는 구나. 
- 안을 까보니 JacksonTester, GsonTester 모두 AbstractJsonMarshalTester를 상속받아 구현하고 있어 사용법은 실질적으로 같다.
- 이상한점은 JacksonTester, GsonTester 모두 Bean이 아닌데 @Autowired 했을때 JacksonTester는 잘되고 GsonTester는 안된다.
- Json에 대한 값 검증이 필요할때 써보면 유용하겠다.
- 코드의 내용은 대체적으로 쉬워서 코드만 봐도 이해가능!

# 테스트장을 마치며
- 테스트 어노테이션관련 정리를 해보았다. 책에서도 모든 내용을 다 다루진 않았겠지만, 해당 내용을 다 한번씩 사용해 보았고, 내가 개발하다 필요한 부분을 잘 사용할 수 있도록 정확히 이해하고 넘어가면 좋을 것 같다. 
