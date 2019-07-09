package com.dave.springbootstudy

import org.junit.jupiter.api.Test
import java.util.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

class TreeSetTest {
	@Test
	fun treeSet() {
		val set = TreeSet<Int>(arrayListOf(5, 10, 15, 7, 1, 3, 3, 5, 2))
		assertThat(set, `is`(listOf(1, 2, 3, 5, 7, 10)))
	}
}
