package com.dave.springbootstudy.service

import com.dave.springbootstudy.domain.Book
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class BookRestService(restTemplateBuilder: RestTemplateBuilder) {
	// RestTemplateBuilder를 사용하여 RestTemplate를 생성
	// RestTemplateBuilder는 RestTemplate을 핸들링하는 빌더 객체로 connectionTimeout, ReadTimeOut 설정뿐 아니라 다른 설정을 간편하게 제공
	private val restTemplate: RestTemplate = restTemplateBuilder.rootUri("/rest/test").build()

	// RestTemplate의 Get 방식으로 통신하는 getForObject() 메서드를 사용하여  `/rest/test` URL에 요청을 보내고 요청에 대한 응답을 Book 객체 형식으로 받아옴
	fun getRestBook(): Book? = this.restTemplate.getForObject("/rest/test", Book::class)
}
