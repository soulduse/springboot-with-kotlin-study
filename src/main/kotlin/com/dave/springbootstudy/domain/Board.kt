package com.dave.springbootstudy.domain

import com.dave.springbootstudy.domain.enums.BoardType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.*

@Entity
@Table
data class Board(
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private var idx: Long = 0,

	var title: String,

	var subTitle: String,

	var content: String,

	@Enumerated(EnumType.STRING)
	var boardType: BoardType,

	@OneToOne(fetch = FetchType.LAZY)
	var user: User
)

