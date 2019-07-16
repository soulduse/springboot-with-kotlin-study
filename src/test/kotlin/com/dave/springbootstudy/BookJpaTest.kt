package com.dave.springbootstudy

import com.dave.springbootstudy.domain.Book
import com.dave.springbootstudy.repository.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@DataJpaTest
class BookJpaTest {

	@Autowired
	private lateinit var testEntityManager: TestEntityManager

	@Autowired
	private lateinit var bookRepository: BookRepository

	@Test
	fun `Book 저장하기 테스트`() {
		val book = Book(
				title = BOOT_TEST_TITLE,
				publishedAt = LocalDateTime.now()
		)
		testEntityManager.persist(book)
		assertThat(bookRepository.getOne(book.idx)).isEqualTo(book)
	}

	@Test
	fun `BookList 저장하고 테스트`() {
		val book1 = Book(
				title = "${BOOT_TEST_TITLE}1",
				publishedAt = LocalDateTime.now()
		)
		testEntityManager.persist(book1)

		val book2 = Book(
				title = "${BOOT_TEST_TITLE}2",
				publishedAt = LocalDateTime.now()
		)
		testEntityManager.persist(book2)

		val book3 = Book(
				title = "${BOOT_TEST_TITLE}3",
				publishedAt = LocalDateTime.now()
		)
		testEntityManager.persist(book3)

		val books = bookRepository.findAll()
		assertThat(books).hasSize(3)
		assertThat(books).contains(book1, book2, book3)
	}

	@Test
	fun `BookList 저장하고 삭제 테스트`() {
		val book1 = Book(
				title = "${BOOT_TEST_TITLE}1",
				publishedAt = LocalDateTime.now()
		)
		testEntityManager.persist(book1)

		val book2 = Book(
				title = "${BOOT_TEST_TITLE}2",
				publishedAt = LocalDateTime.now()
		)
		testEntityManager.persist(book2)

		bookRepository.deleteAll()
		assertThat(bookRepository.findAll()).isEmpty()
	}

	companion object {
		private const val BOOT_TEST_TITLE = "Spring Boot Test Book"
	}
}
