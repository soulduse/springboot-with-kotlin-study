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
