package com.dave.springbootstudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringbootstudyApplication

fun main(args: Array<String>) {
	runApplication<SpringbootstudyApplication>(*args)
}

@Bean
fun holoman() = Holoman(
		name = "Kim",
		howLong = 35
)
