package com.dave.springbootstudy.config

import com.dave.springbootstudy.oauth.ClientResource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.web.filter.CharacterEncodingFilter

/**
 * - 각 소셜 미디어의 프로퍼티 값을 호출하는 빈을 등록함.
 * - 소셜 미디어 리소 정보는 시큐리티 설정에서 사용하기 때문에 빈으로 등록함.
 * - 4개의 소셜 미디어 프로퍼티를 @ConfigurationProperties 어노테이션을 사용하여 바인딩함.
 * - @ConfigurationProperties를 사용하지 않았다면 일일이 프로퍼티 값을 불러와야 했을 것이다.
 */
@Configuration
@EnableWebSecurity // 웹에서 시큐리티 기능을 사용하겠다라고 선언
class SecurityConfig: WebSecurityConfigurerAdapter() {
	// 요청, 권한, 기타 설정에 대해서는 필수적으로 최적화한 설정이 들어가야 함.
	// 최적화를 위해서 WebSecurityConfigurerAdapter 상속후 configure 오버라이드 후 시큐리티 설정
	override fun configure(http: HttpSecurity) {
		val filter = CharacterEncodingFilter()
		http
			.authorizeRequests() // 인증 메커니즘을 요청한 HttpServletRequest 기반으로 설정
				// 요청 패턴을 리스트 형식으로 설정
				.antMatchers("/", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**")
				// 설정한 리퀘스트 패턴을 누구나 접근 할 수 있도록 허용
				.permitAll()
				// 설정한 요청 이외의 리퀘스트 요청을 표현
				.anyRequest()
				// 해당 요청은 인증된 사용자만 사용 가능
				.authenticated()
			.and()
				// 응답에 해당하는 header를 설정. 설정하지 않으면 Default 값으로 설정됨.
				.headers()
				// XFrameOptionsHeaderWriter의 최적화 설정을 허용하지 않음.
				.frameOptions().disable()
			.and()
				.exceptionHandling()
				// 인증의 진입지점. 인증되지 않은 사용자가 허용되지 않은 경로로 리퀘스트 요청시 '/login'으로 이동됨
				.authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/login"))
			.and()
				// 로그인에 성공하면 설정된 경로로 포워딩
				.formLogin()
				.successForwardUrl("/board/list")
			.and()
				// 로그아웃이 대한 설정
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/") // 로그아웃 성공시 포워딩될 URL
				.deleteCookies("JSESSIONID") // 로그아웃 성공시 삭제될 쿠키값
				.invalidateHttpSession(true) // 설정된 세션 무효화를 수행
			.and()
				.addFilterBefore(filter, CsrfFilter::class.java) // 첫 번째 인자보다 먼저 시작될 필터를 등록
				.csrf().disable() // 문자 인코딩 필터보다 CsrfFilter를 먼저 실행하도록 설정
	}

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