package com.dave.springbootstudy

import com.dave.springbootstudy.controller.BookController
import com.dave.springbootstudy.domain.Book
import com.dave.springbootstudy.service.BookService
import org.hamcrest.Matchers.contains
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import java.time.LocalDateTime
import org.mockito.BDDMockito.given
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

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
		given(bookService.getBookList()).willReturn(arrayListOf(book))
		mvc.perform(get("/books"))
				.andExpect(status().isOk)
				.andExpect(view().name("book"))
				.andExpect(model().attributeExists("bookList"))
				.andExpect(model().attribute("bookList", contains(book)))
	}
}
