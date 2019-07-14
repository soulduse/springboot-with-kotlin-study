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
		println(value)
//		println(propertyValue)
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
