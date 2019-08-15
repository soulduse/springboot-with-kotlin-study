package com.dave.springbootstudy.controller

import com.dave.springbootstudy.annotation.SocialUser
import com.dave.springbootstudy.domain.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
	@GetMapping("/login")
	fun login() = "login"

	@GetMapping("/{facebook|google|kakao|naver}/complete")
	fun loginComplete(@SocialUser user: User): String {
		return "redirect:/board/list"
	}
}
