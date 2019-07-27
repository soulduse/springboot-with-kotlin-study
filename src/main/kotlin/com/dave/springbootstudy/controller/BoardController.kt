package com.dave.springbootstudy.controller

import com.dave.springbootstudy.service.BoardService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/board") // API URL 경로를 '/board'로 정의
class BoardController(
	// boardService 의존성 주입
	private val boardService: BoardService
) {

	@GetMapping("", "/") // 매핑경로 다중화
	fun board(
		// RequestParam 어노테이션 사용하여 idx 값을 받음 값이 없으면 기본값 0, 0으로 board를 조회하면 null이 리턴됨.
		@RequestParam(value = "idx", defaultValue = "0")
		idx: Long,
		model: Model
	): String {
		model.addAttribute("board", boardService.findBoardByIdx(idx))
		return "/board/form"
	}

	@GetMapping("/list")
	fun list(
		// PageableDefault를 사용하면 파라미터인 size, sort, direction 등을 사용하여 페이징 처리에 대한 규약을 정의 가능
		@PageableDefault
		pageable: Pageable,
		model: Model
	): String {
		model.addAttribute("boards", boardService.findBoards(pageable))
		return "/board/list" // src/resource/template를 기준으로 데이터 바인딩할 타깃뷰 경로 지정
	}
}
