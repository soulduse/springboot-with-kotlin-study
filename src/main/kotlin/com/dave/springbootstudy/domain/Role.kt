package com.dave.springbootstudy.domain

// 스프링 시큐리티에서는 항상 권한 코드에 ROLE_이 앞에 있어야한다.
enum class Role(val key: String, val title: String) {
	GUEST("ROLE_GUEST", "손님"),
	USER("ROLE_USER", "사용자"),
	MANAGER("ROLE_MANAGER", "매니저"),
	ADMIN("ROLE_ADMIN", "관리자")
}
