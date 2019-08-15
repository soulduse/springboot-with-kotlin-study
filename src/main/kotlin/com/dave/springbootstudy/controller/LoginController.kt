package com.dave.springbootstudy.controller

import com.dave.springbootstudy.domain.User
import com.dave.springbootstudy.domain.enums.SocialType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.time.Instant
import javax.servlet.http.HttpSession

@Controller
class LoginController {
	@GetMapping("/login")
	fun login() = "login"

	@GetMapping("/{facebook|google|kakao|naver}/complete")
	fun loginComplete(session: HttpSession): String {
		// SecurityContextHolder에서 인증된 정보를 OAuth2Authentication 형태로 받음
		// OAuth2Authentication은 기본적인 인증에 대한 정보뿐 아니라 OAuth2 인증과 관련된 정보도 함께 제공함.
		val authentication = SecurityContextHolder.getContext().authentication as OAuth2Authentication
		// 리소스에서 받은 정보를 details를 사용해 map 타입으로 받을 수 있다.
		val map = authentication.userAuthentication.details as Map<String, String>
		session.setAttribute(
			"user",
			User(
				name = map["name"]!!,
				email = map["email"]!!,
				principal = map["id"]!!,
				socialType = SocialType.FACEBOOK,
				createdAt = Instant.now()
			)
		)
		return "redirect:/board/list"
	}

	@GetMapping("/{facebook|google|kakao|naver}/complete")
	fun loginComplete(@SocialUser user: User): String {
		return "redirect:/board/list"
	}
}
