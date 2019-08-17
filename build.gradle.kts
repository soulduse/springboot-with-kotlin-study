import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
	kotlin("plugin.jpa") version "1.3.31"
	id("org.springframework.boot") version "2.1.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	id("org.jetbrains.kotlin.kapt") version "1.3.31"
	kotlin("jvm") version "1.3.31"
	kotlin("plugin.spring") version "1.3.31"
}

group = "com.dave"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("pl.allegro.tech.boot:handlebars-spring-boot-starter:0.3.0")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	runtimeOnly("com.h2database:h2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito:mockito-core:3.0.0")


}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
