package com.dave.springbootstudy.domain

import java.time.LocalDateTime
import javax.persistence.*


data class BookB(
		private val idxField: Int = 0,
		private val titleField: String,
		private val publishedAtField: LocalDateTime
) {
	val idx: Int get() = idxField
	val title get() = titleField
	val publishedAt get() = publishedAtField
}

// 어떤 방식이 좋을까 ..? 항상 데이터 클래스를 만들면 고민되는 것
@Entity
@Table
class Book(
		title: String,
		publishedAt: LocalDateTime?
) {
	@Id
	@GeneratedValue
	var idx: Int = 0
		private set
	@Column
	var title: String = title
		private set
	@Column
	var publishedAt: LocalDateTime? = publishedAt
		private set

	override fun toString(): String {
		return "BookB(idx=$idx, title='$title', publishedAt=$publishedAt)"
	}
}
