package com.dave.springbootstudy.config.auth

// 어노테이션이 생성될 수 있는 위치를 지정
// PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginUser
