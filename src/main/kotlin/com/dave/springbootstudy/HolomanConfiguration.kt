package com.dave.springbootstudy

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HolomanConfiguration {

    @Bean
    fun holoman(): Holoman = Holoman(
            howLong = 5,
            name = "Dave"
    )
}
