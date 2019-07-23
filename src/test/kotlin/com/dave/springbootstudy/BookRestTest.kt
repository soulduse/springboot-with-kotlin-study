package com.dave.springbootstudy

import com.dave.springbootstudy.service.BookRestService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withServerError
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.HttpServerErrorException

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
				.andRespond(withServerError())
		this.thrown.expect(HttpServerErrorException::class.java)
		this.bookRestService.getRestBook()
	}
}
