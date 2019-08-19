package com.dave.springbootstudy.domain

import com.dave.springbootstudy.domain.base.BaseTimeEntity
import javax.persistence.*

@Entity
class User(
	name: String,
	email: String,
	picture: String,
	role: Role
): BaseTimeEntity() {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long = 0
		private set
	@Column(nullable = false)
	var name: String = name
		private set
	@Column(nullable = false)
	var email: String = email
		private set
	var picture: String = picture
		private set
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	var role: Role = role
		private set

	val roleKey: String
		get() = role.key

	fun update(name: String, picture: String): User {
		this.name = name
		this.picture = picture
		return this
	}
}
