package com.dave.springbootstudy.domain.enums

enum class SocialType {
	FACEBOOK,
	GOOGLE,
	KAKAO,
	NAVER;

	private val ROLE_PREFIX = "ROLE_"

	val roleType: String
		get() = ROLE_PREFIX + name

	fun isEquals(authority: String) = this.roleType == authority
}
