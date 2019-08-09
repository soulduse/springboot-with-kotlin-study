package com.dave.springbootstudy.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.*

@Entity
@Table
class User(
	name: String,
	password: String,
	email: String,
	updatedAt: Instant,
	createdAt: Instant
) {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var idx: Long = 0
		private set

	var name: String = name
		private set

	var password: String = password
		private set

	var email: String = email
		private set

	@CreatedDate
	var updatedAt: Instant = updatedAt
		private set

	@LastModifiedDate
	var createdAt: Instant = createdAt
		private set

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as User

		if (idx != other.idx) return false
		if (name != other.name) return false
		if (password != other.password) return false
		if (email != other.email) return false
		if (updatedAt != other.updatedAt) return false
		if (createdAt != other.createdAt) return false

		return true
	}

	override fun hashCode(): Int {
		var result = idx.hashCode()
		result = 31 * result + name.hashCode()
		result = 31 * result + password.hashCode()
		result = 31 * result + email.hashCode()
		result = 31 * result + updatedAt.hashCode()
		result = 31 * result + createdAt.hashCode()
		return result
	}

	override fun toString(): String {
		return "User(idx=$idx, name='$name', password='$password', email='$email', updatedAt=$updatedAt, createdAt=$createdAt)"
	}
}