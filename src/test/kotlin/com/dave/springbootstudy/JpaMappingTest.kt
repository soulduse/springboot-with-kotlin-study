package com.dave.springbootstudy

import com.dave.springbootstudy.domain.Board
import com.dave.springbootstudy.domain.User
import com.dave.springbootstudy.domain.enums.BoardType
import com.dave.springbootstudy.repository.BoardRepository
import com.dave.springbootstudy.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant

@RunWith(SpringRunner::class)
@DataJpaTest
class JpaMappingTest {
	private val boardTestTitle = "테스트"
	private val email = "test@gmail.com"

	@Autowired
	lateinit var userRepository: UserRepository

	@Autowired
	lateinit var boardRepository: BoardRepository

	@Before
	fun init() {
		val user = userRepository.save(
			User(
				name = "Dave",
				password = "test",
				email = email,
				createdAt = Instant.now(),
				updatedAt = Instant.now()
			)
		)

		boardRepository.save(
			Board(
				title = boardTestTitle,
				subTitle = "서브 타이틀",
				content = "콘텐츠",
				boardType = BoardType.FREE,
				createdAt = Instant.now(),
				updatedAt = Instant.now(),
				user = user
			)
		)
	}

	@Test
	fun `제대로 생성 되었나`() {
		val user = userRepository.findByEmail(email)
		assertThat(user.name).isEqualTo("Dave")
		assertThat(user.password).isEqualTo("test")
		assertThat(user.email).isEqualTo(email)

		val board = boardRepository.findByUser(user)
		assertThat(board.title).isEqualTo(boardTestTitle)
		assertThat(board.subTitle).isEqualTo("서브 타이틀")
		assertThat(board.content).isEqualTo("콘텐츠")
		assertThat(board.boardType).isEqualTo(BoardType.FREE)
	}

}
