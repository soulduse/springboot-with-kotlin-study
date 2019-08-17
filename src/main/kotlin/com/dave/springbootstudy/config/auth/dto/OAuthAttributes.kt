package com.dave.springbootstudy.config.auth.dto

import com.dave.springbootstudy.domain.Role
import com.dave.springbootstudy.domain.User

class OAuthAttributes(
	attributes: Map<String, Any>,
	nameAttributeKey: String,
	name: String,
	email: String,
	picture: String
) {
	var attributes: Map<String, Any> = attributes
		private set
	var nameAttributeKey: String = nameAttributeKey
		private set
	var name: String = name
		private set
	var email: String = email
		private set
	var picture: String = picture
		private set

	fun toEntity() = User(
		name = name,
		email = email,
		picture = picture,
		role = Role.GUEST
	)

	companion object {
		fun of(
			registrationId: String,
			userNameAttributeName: String,
			attributes: Map<String, Any>
		): OAuthAttributes {
			return ofGoogle(userNameAttributeName, attributes)
		}

		private fun ofGoogle(
			userNameAttributeName: String,
			attributes: Map<String, Any>
		): OAuthAttributes {
			return OAuthAttributes(
				name = attributes["name"] as String,
				email = attributes["email"] as String,
				picture = attributes["picture"] as String,
				attributes = attributes,
				nameAttributeKey = userNameAttributeName
			)
		}
	}
}
