package com.dave.springbootstudy

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(
		value = ["value=test"],
//		properties = ["property.value=propertyTest"],
		classes = [SpringbootstudyApplication::class],
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class SpringbootstudyApplicationTests {

	@Value("\${value}")
	private lateinit var value: String

//	@Value("\${property.value}")
//	private lateinit var propertyValue: String

	@Test
	fun contextLoads() {
		println(value)
//		println(propertyValue)
	}
}
