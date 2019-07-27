package com.dave.springbootstudy

import com.dave.springbootstudy.domain.Board
import com.dave.springbootstudy.domain.User
import com.dave.springbootstudy.domain.enums.BoardType
import com.dave.springbootstudy.repository.BoardRepository
import com.dave.springbootstudy.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Instant

@SpringBootApplication
class SpringbootstudyApplication {

	@Bean
	fun runner(
		userRepository: UserRepository,
		boardRepository: BoardRepository
	): CommandLineRunner {
		return CommandLineRunner {
			val user = userRepository.save(
				User(
					name = "Dave",
					password = "test",
					email = "dave@gmail.com",
					createdAt = Instant.now(),
					updatedAt = Instant.now()
				)
			)

			(1 .. 200).forEachIndexed { index, _ ->
				boardRepository.save(
					Board(
						title = "게시글 $index",
						subTitle = "서브 타이틀 $index",
						content = "콘텐츠",
						boardType = BoardType.FREE,
						createdAt = Instant.now(),
						updatedAt = Instant.now(),
						user = user
					)
				)
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<SpringbootstudyApplication>(*args)
}
