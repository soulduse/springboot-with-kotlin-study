package com.dave.springbootstudy.oauth

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails

/**
 * @NestedConfigurationProperty
 * ㄴ> 해당 필드가 단일값이 아닌 중복으로 바인딩된다고 표시하는 어노테이션
 * ㄴ> 소셜 미디어 세 여러 곳의 프로퍼티를 각각 바인딩하므로 사용함.
 *
 * AuthorizationCodeResourceDetails
 * ㄴ> yaml에서 설정해준 소셜의 프로퍼티값 중 'client'를 기준으로 하위의 key/value를 매핑해주는 대상 객체
 *
 * ResourceServerProperties
 * ㄴ> OAuth2 리소스값을 매핑하는데 사용하지만, 회원정보를 얻는 userInfoUri 값을 받는데 사용됨.
 */
data class ClientResource(
	@NestedConfigurationProperty
	val client: AuthorizationCodeResourceDetails = AuthorizationCodeResourceDetails(),
	@NestedConfigurationProperty
	val resource: ResourceServerProperties = ResourceServerProperties()
)
