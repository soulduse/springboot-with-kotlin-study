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




