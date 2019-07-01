package com.dave.springbootstudy

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("holoman")
class HolomanPropertices {
    lateinit var name: String
    var howLong: Int = 0
}

//data class HolomanPropertices (
//        val name: String,
//        val howLong: Int
//)
