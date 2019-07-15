# 4일차
----

## 스프링 부트 테스트
- 스프링 부트에서 크게 두가지 테스트 스타터 모듈로 구성됨.
- spring-boot-test, spring-boot-test-autoconfigure
- @SprigBootTest, @WebMvcTest, @DataJpaTest, @RestClientTest, @JsonTest에 대해 학습해보자


### @SprigBootTest
- 실제 구동되는 애플리케이션과 동일하게 로드 > 테스트 진행
- 설정된 모든 빈을 로드하기 때문에 무겁다 (어플리케이션이 크면 클수록 더 무거워짐)
- 단위 테스트의 성격과는 맞지 않다.

기본 제공되는 테스트 코드
```kotlin
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringbootstudyApplicationTests {
	@Test
	fun contextLoads() {

	}
}
```
- @RunWith JUnit에 내장된 러너를 사용하는 대신 어노테이션에 정의돈 러너 클래스를 사용.
- @SpringBootTest 어노테이션을 사용하려면 JUnit 실행에 필요한 SpringJUnit4ClassRunner 클래스를 상속받은 @RunWith(SpringRunner::class)를 꼭 붙여서 사용.
 
> @SpringBootTest 파라미터 사용해보기 
```kotlin
@RunWith(SpringRunner::class)
@SpringBootTest(
		value = ["value=test"],
//		properties = ["property.value=propertyTest"],
		classes = [SpringbootstudyApplication::class],
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class SpringbootstudyApplicationTests {

	@Value("\${value}")
	private lateinit var value: String

//	@Value("\${property.value}")
//	private lateinit var propertyValue: String

	@Test
	fun contextLoads() {
	}
}
```
Java에서와는 달리 @SpringBootTest @Value 어노테이션을 사용하려면 파라미터 사용방식이 약간은 다르다.
[참고](https://blog.jetbrains.com/idea/2018/10/spring-kotlin-references-in-value-annotation/)
#### java @SpringBootTest
```java  
@SpringBootTest(
    value = "value=test",
    properties= {"property.value=propertyTest"},
    classes = {SpringbootstudyApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
```
#### kotlin @SpringBootTest
```kotlin
@SpringBootTest(
    value = ["value=test"],
    properties = ["property.value=propertyTest"],
    classes = [SpringbootstudyApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
```

#### java @Value
```java  
@Value("${value}")
private String value;
)
```
#### kotlin @Value
```kotlin
@Value("\${value}")
private lateinit var value: String
```

```text
- value : 테스트가 실행되기 전에 적용할 프로퍼티 추가가능
- properties : 테스트가 실행되기 전에 {key=value} 형식으로 프로퍼티 추가 가능
- classes 애플리케이션 컨텍스트에 로드할 클래스를 지정할 수 있음. (따로 지정하지 않을시 @SpringBootConfiguration을 찾아서 로드함.
- webEnvironment : 애플리이션 웹 환경 설정 
```

# 궁금했던 것 과 알게된 것
@SprigBootTest 에 대해서 간략하게 파보는 시간이었다. 각종 프로퍼티들은 어떤 경우 사용하게 되고 유용할까? 단순히 상수가 필요한 경우이거나, 프로퍼티에 설정을 가져올때나 사용하게 될까? 의문이 들었다.
> 찾아보니 SpringBoot는 기본적으로 클래스 경로상에 (application.properties, application.yml)를 통해서 애플리케이션 설정을 수행하는데, 테스트에는 기존 설정과 달라질 필요가 있는 경우가 많다고 한다.
이럴때 사용하면 괜찮을 것 같다.
