package com.dave.springbootstudy.domain.base

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseTimeEntity {
	@CreatedDate
	val createdAt: Instant = Instant.now()
	@LastModifiedDate
	val updatedAt: Instant = Instant.now()
}
