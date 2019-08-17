package com.dave.springbootstudy.controller

import com.dave.springbootstudy.config.auth.dto.SessionUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpSession

@Controller
class IndexController(
	private val httpSession: HttpSession
) {
	@GetMapping("/")
	fun index(model: Model): String {
		val user: SessionUser? = httpSession.getAttribute("user") as? SessionUser
		user?.let { model.addAttribute("userName", user.name) }
		println("user data is $user")
		return "index"
	}
}
