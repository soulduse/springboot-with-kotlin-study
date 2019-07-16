package com.dave.springbootstudy.repository

import com.dave.springbootstudy.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Int> {
}
