# 6일차
----

## @DataJpaTest
- 이름 답게 JPA 관련 테스트 설정만 로드 함.
- JPA를 사용하여 생성, 수정, 삭제 등의 테스트 가능
- 인메모리 임베디드 데이터베이스를 사용함
- @Entity 클래스를 스캔하여 JPA 저장소를 구성

```kotlin
@ActiveProfiles
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Replace.Any => 기본 내장된 데이터소스를 사용.
// Replace.NONE => @ActiveProfiles에 설정한 프로파일 환경값에 따라 데이터소스 적용.
// 이 외에도 application.yml에서 프로퍼티 설정을 spring.database.replace: NONE 으로 변경하면 사용가능
```

- @DataJpaTest는 테스트가 끝날때 마다 자동으로 테스트에 사용한 데이터를 롤백한다.
- JPA 테스트가 끝났을 때 실제 데이터가 변경 되었는지 걱정할 필요 없음.

```kotlin
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

```

# 궁금했던 것 과 알게된 것
- @DataJpaTest는 JPA 관련 테스트 하는 것이다.
- TestEntityManager에 데이터만 넣었는데 어떻게 저렇게 알아서 들어가지 했는데, @DataJpaTest를 사용할 경우에 TestEntityManager 클래스가 자동으로 빈으로 등록 된다고 한다.
- 만약 실제 DB를 테스트 해보려면 어떻게 해야될까? > 위에 적은 주석처럼 @AutoConfigureTestDatabase(replace = Replace.NONE)을 사용하면 된다.
- 테스트를 돌려보니 느릴 줄 알았는데 생각보다 빠르다. 이유는 JPA 관련 테스트 설정만 로드해서!
- TestEntityManager에는 쓸만한 내용이 많다 
