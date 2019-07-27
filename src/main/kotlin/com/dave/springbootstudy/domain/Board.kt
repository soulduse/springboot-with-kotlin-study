package com.dave.springbootstudy.domain

import com.dave.springbootstudy.domain.enums.BoardType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.*

@Entity
@Table
class Board(
	title: String,
	subTitle: String,
	content: String,
	boardType: BoardType,
	createdAt: Instant,
	updatedAt: Instant,
	user: User
) {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var idx: Long = 0
		private set

	var title: String = title
		private set

	var subTitle: String = subTitle
		private set

	var content: String = content
		private set

	@Enumerated(EnumType.STRING)
	var boardType: BoardType = boardType
		private set

	@CreatedDate
	var createdAt: Instant = createdAt
		private set

	@LastModifiedDate
	var updatedAt: Instant = updatedAt
		private set

	@OneToOne(fetch = FetchType.LAZY)
	var user: User = user
		private set

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Board

		if (idx != other.idx) return false
		if (title != other.title) return false
		if (subTitle != other.subTitle) return false
		if (content != other.content) return false
		if (boardType != other.boardType) return false
		if (createdAt != other.createdAt) return false
		if (updatedAt != other.updatedAt) return false
		if (user != other.user) return false

		return true
	}

	override fun hashCode(): Int {
		var result = idx.hashCode()
		result = 31 * result + title.hashCode()
		result = 31 * result + subTitle.hashCode()
		result = 31 * result + content.hashCode()
		result = 31 * result + boardType.hashCode()
		result = 31 * result + createdAt.hashCode()
		result = 31 * result + updatedAt.hashCode()
		result = 31 * result + user.hashCode()
		return result
	}

	override fun toString(): String {
		return "Board(idx=$idx, title='$title', subTitle='$subTitle', content='$content', boardType=$boardType, createdAt=$createdAt, updatedAt=$updatedAt, user=$user)"
	}
}
