package com.dave.springbootstudy.domain

import java.time.LocalDateTime

data class Book(
		private val idxField: Int = 0,
		private val titleField: String,
		private val publishedAtField: LocalDateTime
) {
	val idx get() = idxField
	val title get() = titleField
	val publishedAt get() = publishedAtField
}

// 어떤 방식이 좋을까 ..? 항상 데이터 클래스를 만들면 고민되는 것
class BookB(
		idx: Int = 0,
		title: String,
		publishedAt: LocalDateTime
) {
	var idx: Int  = idx
		private set
	var title: String = title
		private set
	var publishedAt: LocalDateTime = publishedAt
		private set

	override fun toString(): String {
		return "BookB(idx=$idx, title='$title', publishedAt=$publishedAt)"
	}
}
