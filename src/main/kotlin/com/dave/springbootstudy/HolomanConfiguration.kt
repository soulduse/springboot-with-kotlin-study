package com.dave.springbootstudy

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(HolomanPropertices::class)
class HolomanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun holoman(propertices: HolomanPropertices): Holoman = Holoman(
            name = propertices.name,
            howLong = propertices.howLong
    )
}
