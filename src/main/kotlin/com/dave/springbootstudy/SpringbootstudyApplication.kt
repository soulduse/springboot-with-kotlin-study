package com.dave.springbootstudy

import com.dave.springbootstudy.resolver.UserArgumentResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@SpringBootApplication
class SpringbootstudyApplication: WebMvcConfigurerAdapter() {
	// UserArgumentResolver 클래스를 적용하려면 WebMvcConfigurerAdapter를 상속받아야 함.
	@Autowired
	private lateinit var userArgumentResolver: UserArgumentResolver

	override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>?) {
		argumentResolvers?.add(userArgumentResolver)
	}
}

fun main(args: Array<String>) {
	SpringApplication.run(SpringbootstudyApplication::class.java, *args)
}
