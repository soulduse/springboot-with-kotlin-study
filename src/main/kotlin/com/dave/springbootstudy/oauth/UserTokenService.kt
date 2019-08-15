package com.dave.springbootstudy.oauth

import com.dave.springbootstudy.domain.enums.SocialType
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

/**
 * UserInfoTokenServices
 * ㄴ> 스프링 시큐리티 OAuth2에서 제공하는 클래스
 * ㄴ> 유저 정보를 얻어오기 위해 소셜 서버와 통신하는 역할 수행
 *
 * URI와 clientId 정보가 필요함.
 * 4개의 소셜 미디어 정보를 SocialType을 기준으로 관리할 것이기 때문에 약간의 커스텀이 필요함.
 *
 * 1. UserInfoTokenServices자 생성자에서 super를 사용하여 각각의 소셜 미디어 정보를 주입한다.
 * 2. 생성자로 받은 socialType으로 OAuth2AuthoritiesExtractor로 넘겨주면 네이밍을 알아서 일괄적으로 처리하도록 설정됨.
 */
class UserTokenService(
	resources: ClientResources,
	socialType: SocialType
) : UserInfoTokenServices(resources.resource.userInfoUri, resources.client.clientId) {

	init {
		setAuthoritiesExtractor(OAuth2AuthoritiesExtractor(socialType))
	}

	companion object {
		class OAuth2AuthoritiesExtractor(private val socialType: SocialType): AuthoritiesExtractor {
			override fun extractAuthorities(map: MutableMap<String, Any>?): MutableList<GrantedAuthority> {
				return AuthorityUtils.createAuthorityList(socialType.roleType)
			}
		}
	}
}
