package com.dave.springbootstudy

import com.dave.springbootstudy.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.test.context.junit4.SpringRunner

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
