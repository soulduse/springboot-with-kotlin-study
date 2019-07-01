# 1일차
----
## @ConditionalOnMissingBean

- 외부 의존성으로 같은 이름을 가진 객체를 주입받을때, 
동일한 이름으로 빈을 등록하여 사용하게 되면 의존성에 주입된 객체의 값이 그대로 사용된다.
ex)
```kotlin
// [A - Project]
// 라이브러리에 존재하는 @Configuration이라 가정.
@Configuration
class HolomanConfiguration {

    @Bean
    fun holoman(): Holoman = Holoman(
            howLong = 5,
            name = "Dave"
    )
}

// [B - Project]
// 내가 사용할 프로젝트에서 아래처럼 @Bean을 재등록해서 덮어쓰기를 시도하면..!
@SpringBootApplication
class SpringbootstudyApplication

fun main(args: Array<String>) {
	runApplication<SpringbootstudyApplication>(*args)
}

@Bean
fun holoman() = Holoman(
		name = "Kim",
		howLong = 35
)
```
위와 같이 정의된 라이브러리가 있고, 이 라이브러리를 의존성 주입(Gradle, Maven 같은)으로 주입받아
@Autowired나 생성자를 통해 값을 주입받게되면 해당 프로젝트에 아래와 같이 중복 등록하여도, 기존에 
라이브러리의 내용을 따라간다. 

```text
프로젝트를 실행하고 컴포턴트 스캔을 통해서 생성된 객체를 살펴보면 
"Holoman(name=Kim, howLong=35)"가 아닌,
"Holoman(name=Dave, howLong=5)"가 출력되는 것을 확인할 수 있다.
```

### 결론은 
#### @ConditionalOnMissingBean 어노테이션을 추가하면 해결된다.
```kotlin
@SpringBootApplication
class SpringbootstudyApplication

fun main(args: Array<String>) {
	runApplication<SpringbootstudyApplication>(*args)
}

@Bean
@ConditionalOnMissingBean
fun holoman() = Holoman(
		name = "Kim",
		howLong = 35
)
```

@ConditionalOnMissingBean 어노테이션이 붙어 있으면 컴포턴트스캔에 이미 등록이 되어있기 때문에 등록을 하지 않게 된다.

----
## Application.properties 활용하여 값 주입하기

아래 처럼 @Bean의 값을 직접 적어주는 방법도 있지만 Application.properties를 활용하여 스태틱한 값을 넣어줄 수 있다.
```kotlin
@Bean
@ConditionalOnMissingBean
fun holoman(): Holoman = Holoman(
        howLong = 5,
        name = "Dave"
)
```

#### Application.properties를 활용한 코드
```kotlin
// HolomanConfiguration.kt
@Bean
@ConditionalOnMissingBean
fun holoman(propertices: HolomanPropertices): Holoman = Holoman(
        name = propertices.name,
        howLong = propertices.howLong
)

// HolomanPropertices.kt
@ConfigurationProperties("holoman")
class HolomanPropertices {
    lateinit var name: String
    var howLong: Int = 0
}

// application.properties
holoman.name = JMT
holoman.how-long = 100
```
