package com.dave.springbootstudy.service

import com.dave.springbootstudy.domain.Book

interface BookService {
	fun getBookList(): List<Book>
}
