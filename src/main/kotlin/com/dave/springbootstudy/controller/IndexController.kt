package com.dave.springbootstudy.controller

import com.dave.springbootstudy.config.auth.LoginUser
import com.dave.springbootstudy.config.auth.dto.SessionUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 * 어플리케이션을 재실행하면 로그인이 풀린다
 * > 세션이 내장 톰캣의 메모리에 저장되기 때문
 * > 메모리에 저자외서 어플리케이션이 재실행 되면 항상 초기화됨
 * > 즉, 배포시마다 초기화
 * > 2대 이상의 서비스에서 서비스를 하고 있다면 톰캣마다 세션 동기화를 설정해야됨
 */
@Controller
class IndexController {
	@GetMapping("/")
	fun index(model: Model, @LoginUser user: SessionUser?): String {
		user?.let { model.addAttribute("userName", user.name) }
		println("user data is $user")
		return "index"
	}
}
