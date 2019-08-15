package com.dave.springbootstudy.config

import com.dave.springbootstudy.oauth.ClientResource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * - 각 소셜 미디어의 프로퍼티 값을 호출하는 빈을 등록함.
 * - 소셜 미디어 리소스 정보는 시큐리티 설정에서 사용하기 때문에 빈으로 등록함.
 * - 4개의 소셜 미디어 프로퍼티를 @ConfigurationProperties 어노테이션을 사용하여 바인딩함.
 * - @ConfigurationProperties를 사용하지 않았다면 일일이 프로퍼티 값을 불러와야 했을 것이다.
 */
@Configuration
class SecurityConfig {

	@Bean
	@ConfigurationProperties("facebook")
	fun facebook() = ClientResource()

	@Bean
	@ConfigurationProperties("google")
	fun google() = ClientResource()

	@Bean
	@ConfigurationProperties("kakao")
	fun kakao() = ClientResource()

	@Bean
	@ConfigurationProperties("naver")
	fun naver() = ClientResource()
}
