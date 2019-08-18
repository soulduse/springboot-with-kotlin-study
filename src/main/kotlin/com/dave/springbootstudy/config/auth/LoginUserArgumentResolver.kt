package com.dave.springbootstudy.config.auth

import com.dave.springbootstudy.config.auth.dto.SessionUser
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpSession

/**
 * 어노테이션 동작을 정의 하는 부분이구나
 * supportsParameter는 아무어노테이션이나 다 허용하면 안되고 특정 어노테이션은 이렇게 동작해야하니
 * 그 제한을 하는 부분이고,
 * resolveArgument는 데이터를 만드는 부분이라고 생각하면 될 것 같다.
 */
@Component
class LoginUserArgumentResolver(
	private val httpSession: HttpSession
): HandlerMethodArgumentResolver {

	/**
	 * - 컨트롤러 메서드의 특정 파라미터를 지원하는지 여부 판단
	 * - 파라미터에 @LoginUser 어노테이션이 붙어있고, 파라미터 클래스 타입이 SessionUser.class인 경우 true
	 */
	override fun supportsParameter(parameter: MethodParameter): Boolean {
		val isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser::class.java) != null
		val isUserClass = SessionUser::class == parameter.parameterType
		return isLoginUserAnnotation && isUserClass
	}

	/**
	 * - 파라미터에 전달할 객체를 생성
	 * - 세션에서 객체를 가져옴
	 */
	override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
		return httpSession.getAttribute("user")
	}
}
