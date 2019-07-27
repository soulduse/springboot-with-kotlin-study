package com.dave.springbootstudy.repository

import com.dave.springbootstudy.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
	fun findByEmail(email: String): User
}
