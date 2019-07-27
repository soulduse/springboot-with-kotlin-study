package com.dave.springbootstudy.repository

import com.dave.springbootstudy.domain.Board
import com.dave.springbootstudy.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
	fun findByUser(user: User): Board
}
