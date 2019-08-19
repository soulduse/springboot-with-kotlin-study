# 9~10일차
----

## 스프링부트 시큐리티 + OAuth2
#### 배경지식
- 스프링부트 시큐리티에 스타터가 제공해주기 때문에 빠른 설정을 할 수 있음.
- 인증(Authentication) + 권한부여(Authorization)
- 사용자가 특정 동작에 관하여 인증된 사용자 인지 확인하는 절차를 말함.
- 보통의 웹사이트에서는 로그인이 인증이라고 생각하면 됨.
- 아래와 같이 다양한 인증방식이 있음.
```text
- Credential 인증 (ID/PW)
- Two-factor 인증 (OTP)
- OAuth2 인증 (소셜미디어 인증)
```

#### OAuth2
- 권한 부여 코드 승인타입(Authorization Code Grant Type)
: 클라이언트가 다른 사용자 대신 특정 리소스에 접근을 요청할 때 사용 => ex) Kakao, Naver, Facebook, Google
- 암시적 승인 타입(Implicit Grant Type)
: 엑세스 토큰을 즉시 반환받아 인증에 사용하는 방식
- 리소스 소유자 암호 자격 증명 승인 타입
: 클라이언트가 암호를 사용하여 액세스 토큰에 대한 사용자의 자격 증명을 교환하는 방식
- 클라이언트 자격 증명 승인 타입
: 클라이언트가 컨텍스트 외부에서 액세스 토큰을 얻어 특정 리소스에 접근을 요청할 때 사용하는 방식


#### 스프링부트 시큐리티 + OAuth2 설계

#### 스프링부트 시큐리티 + OAuth2 의존성

#### 스프링부트 시큐리티 + OAuth2 구현

#### 스프링부트 시큐리티 + OAuth2 설정

## 궁금했던 것 과 알게된 것
### Security
- SecurityConfig 설정은 꽤나 복잡하구나.
- WebSecurityConfigurerAdapter를 상속받아서 Security관련 작업을 할 수 있다.
- @EnableWebSecurity 어노테이션을 사용하면 Spring Security 설정을 활성화 할 수 있다.
- 시큐리티에서 권한과 제한을 다 줄 수 있구나
- 로그인/로그아웃에는 빠질수 없는 설정이라는 생각.

### OAuth2UserService
registrationId, userNameAttributeName등 기본적으로 2.0 시큐리티에서 OAuth2를 사용하기위해
많은 유틸성 클래스들을 준비해두었구나라는 생각. 기본적으로 제공해주는 OAuth도 꽤 되었다. (Google, Github, Facebook, Okata)

### yaml
아래 두개를 헷갈리지 말자.
등록을 하기위한 옵션
- spring.security.oauth2.client.registration.xxx
제공을 위한 옵션(?)
- spring.security.oauth2.client.provider.xxx
