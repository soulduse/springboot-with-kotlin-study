package com.dave.springbootstudy

import org.apache.catalina.startup.Tomcat
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootstudyApplication

fun main(args: Array<String>) {
	runApplication<SpringbootstudyApplication>(*args)
	val tomcat = Tomcat().apply {
		setPort(8080)
		addContext("/", "/")
	}
}
