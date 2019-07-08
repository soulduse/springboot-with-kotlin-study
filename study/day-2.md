# 2일차
----
## 독립적으로 실행 가능한 JAR
"JAR파일 하나로 실행할 수 있다."

- ./gradlew build를 하면 실행가능한 JAR 파일 하나가 생성 됨.
- 스프링 부트의 전략
    - 내장 JAR : 기본적으로 자바에는 내장 JAR를 로딩하는 표준적인 방법이 없음.
    - 애플리케이션 클래스와 라이브러리 위치 구분
    - org.springframework.boot.loader.jar.JarFile을 사용해서 내장 JAR를 읽는다.
    - org.springframework.boot.loader.Launcher를 사용해서 실행한다.

> 스프링에서는 JAR파일 하나로 실행하기 위해서 다양한 시도를 했는것 같다. 예전에는 여러 JAR를 통합해서 실행했다고하는데 얼마나 불편했을까?

## 스프링부트는 Bean을 2단계로 나눠서 읽은 후 등록한다. 
1. 컴포넌트 스캔을 Bean을 스캔하여 첫번째로 등록
2. 1번을 바탕으로 JAR 파일에 들어있는 META Information > Spring.factories > autoConfigurations 를 참조하여 자동설정을 시작

- Springboot 자체가 서버는 아니다. 
> Tomcat, Jetty, Netty, Undertow 등이 서블릿 또는 리액티브 서버역할을 하는 것이지 Springboot는 이를 활용할 뿐(자동 설정으로)


스프링부트 활용
--
### 배너 수정하기
- src > main > resources에 banner.txt 파일을 생성 후 안에 내용을 적고 앱을 실행하면 banner.txt에 입력한 문자열이 출력된다.

