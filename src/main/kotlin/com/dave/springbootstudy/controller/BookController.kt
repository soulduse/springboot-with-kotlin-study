package com.dave.springbootstudy.controller

import com.dave.springbootstudy.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BookController {

	@Autowired
	private lateinit var bookService: BookService

	@GetMapping("/books")
	fun getBookList(model: Model): String {
		model.addAttribute("bookList", bookService.getBookList())
		return "book"
	}
}
