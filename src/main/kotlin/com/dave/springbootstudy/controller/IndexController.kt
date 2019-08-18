package com.dave.springbootstudy.controller

import com.dave.springbootstudy.config.auth.LoginUser
import com.dave.springbootstudy.config.auth.dto.SessionUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
	@GetMapping("/")
	fun index(model: Model, @LoginUser user: SessionUser): String {
		user?.let { model.addAttribute("userName", user.name) }
		println("user data is $user")
		return "index"
	}
}
