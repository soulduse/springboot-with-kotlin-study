package com.dave.springbootstudy.service

import com.dave.springbootstudy.domain.Board
import com.dave.springbootstudy.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BoardService(
	private val boardRepository: BoardRepository
) {
	fun findBoards(pageable: Pageable): Page<Board> {
		val page = if (pageable.pageNumber <= 0) 0 else pageable.pageNumber - 1
		return boardRepository.findAll(PageRequest.of(page, pageable.pageSize))
	}

	fun findBoardByIdx(idx: Long): Board? {
		return boardRepository.findById(idx).orElse(null)
	}
}
