package com.dave.springbootstudy.config

import com.dave.springbootstudy.config.auth.LoginUserArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * 생성한 LoginUserArgumentResolver가 스프링에서 인식할 수 있도록 WebMvcConfigure에 추가 해줘야한다.
 * HandlerMethodArgumentResolver는 항상 WebMvcConfigure의 addArgumentResolvers를 통해 추가해야 함.
 */
@Configuration
class WebConfig(
	private val loginUserArgumentResolver: LoginUserArgumentResolver
): WebMvcConfigurer {
	override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
		resolvers.add(loginUserArgumentResolver)
	}
}
