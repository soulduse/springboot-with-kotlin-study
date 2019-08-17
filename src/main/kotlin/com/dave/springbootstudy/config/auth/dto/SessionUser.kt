package com.dave.springbootstudy.config.auth.dto

import com.dave.springbootstudy.domain.User

data class SessionUser (private val user: User) {
	val name: String = user.name
	val email: String = user.email
	val picture: String = user.picture
}
