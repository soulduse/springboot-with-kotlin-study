package com.dave.springbootstudy.oauth

import com.dave.springbootstudy.config.auth.dto.OAuthAttributes
import com.dave.springbootstudy.config.auth.dto.SessionUser
import com.dave.springbootstudy.domain.User
import com.dave.springbootstudy.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpSession

@Service
class CustomOAuth2UserService(
	private val userRepository: UserRepository,
	private val httpSession: HttpSession
): OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
		val delegate = DefaultOAuth2UserService()
		val oAuth2User = delegate.loadUser(userRequest)

		// 현재 로그인 진행중인 서비스를 구분하는 코드
		// 네이버인지 구글인지 구분하기 위해 사용됨.
		val registrationId = userRequest.clientRegistration.registrationId
		// OAuth2 로그인 진행시 키가 되는 필드값을 이야기함
		// PK와 같은 의미
		// 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오등은 기본 지원하지 않음
		// 이후 네이버 로그인과 구글 로그인등을 동시 지원할 때 사용됨.
		val userNameAttributeName = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
		// OAuthAttributes: OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담은 클래스
		// 네이버 등 다른 소셜 로그인도 이 클래스를 사용함.
		val attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.attributes)

		val user = saveOrUpdate(attributes)
		// SessionUser: 세선에 사용자 정보를 저장하기 위한 Dto
		httpSession.setAttribute("user", SessionUser(user))

		return DefaultOAuth2User(
			Collections.singleton(SimpleGrantedAuthority(user.roleKey)),
			attributes.attributes,
			attributes.nameAttributeKey
		)
	}

	private fun saveOrUpdate(attributes: OAuthAttributes): User {
		val user = userRepository.findByEmail(attributes.email)
			?.update(attributes.name, attributes.picture)
			?: attributes.toEntity()
		return userRepository.save(user)
	}
}
