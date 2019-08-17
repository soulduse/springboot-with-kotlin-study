package com.dave.springbootstudy.config.auth.dto

import com.dave.springbootstudy.domain.User
import java.io.Serializable

class SessionUser (user: User): Serializable {
	val name: String = user.name
	val email: String = user.email
	val picture: String = user.picture

	override fun toString(): String {
		return "SessionUser(name='$name', email='$email', picture='$picture')"
	}
}
