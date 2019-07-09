# 3일차
----

## 자동 환경 설정 이해
@SpringBootApplication은 
(@SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan)어노테이션의 조합 조합
- @SpringBootConfiguration
- @EnableAutoConfiguration
- @ComponentScan


#### @SpringBootConfiguration
- 스프링부트의 설정을 나타내는 어노테이션
- @Configuration을 대체하며 스프링부트 전용으로 사용.

#### @EnableAutoConfiguration
- 자동 설정 핵심 어노테이션
- 특별한 값이 없을때는 기본값을 사용하도록 설계

#### @ComponentScan
- 특정 패키지를 경로를 기반으로 @Component 설정 클래스를 찾아 설정.


#### /META-INF/spring-configuration-metadata.json를 살펴보면
spring.h2.console의 경우 아래와 같은 json을 포함한다.
빈을 등록하여 사용하려면 어떻게 해야할까?

```json
{
  "name": "spring.h2.console.enabled",
  "type": "java.lang.Boolean",
  "description": "Whether to enable the console.",
  "sourceType": "org.springframework.boot.autoconfigure.h2.H2ConsoleProperties",
  "defaultValue": false
},
{
  "name": "spring.h2.console.path",
  "type": "java.lang.String",
  "description": "Path at which the console is available.",
  "sourceType": "org.springframework.boot.autoconfigure.h2.H2ConsoleProperties",
  "defaultValue": "\/h2-console"
},
{
  "name": "spring.h2.console.settings.trace",
  "type": "java.lang.Boolean",
  "description": "Whether to enable trace output.",
  "sourceType": "org.springframework.boot.autoconfigure.h2.H2ConsoleProperties$Settings",
  "defaultValue": false
},
{
  "name": "spring.h2.console.settings.web-allow-others",
  "type": "java.lang.Boolean",
  "description": "Whether to enable remote access.",
  "sourceType": "org.springframework.boot.autoconfigure.h2.H2ConsoleProperties$Settings",
  "defaultValue": false
},
```
힌트는 application.yaml 파일만 변경하면된다.
위에 json으로 유추할 수 있는 것은 `spring.h2.console.enabled`의 기본 값이 false로 되어있는것을 볼 수 있는데,
application.yaml에서 아래와 같이 작성하면 사용가능.
```yaml
spring:
    h2:
      console:
        enabled: true
```
결론은 내가 사용하고자 하는 빈중 스프링에서 기본적으로 설정되어있는 것들이 있다면, 해당 설정 파일을 보고 사용법을 유추 할 수 있을것 같다.
그리고 application 설정값만 변경하여 간단하게 사용 가능하다. 


