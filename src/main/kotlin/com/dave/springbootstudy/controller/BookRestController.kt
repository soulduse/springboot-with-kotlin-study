package com.dave.springbootstudy.controller

import com.dave.springbootstudy.domain.Book
import com.dave.springbootstudy.service.BookRestService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookRestController(private val bookRestService: BookRestService) {
	@GetMapping(path = ["/rest/test"], produces = [MediaType.APPLICATION_JSON_VALUE])
	fun getRestBooks(): Book? = bookRestService.getRestBook()
}
