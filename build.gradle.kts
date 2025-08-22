plugins {
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.jetbrains.kotlin.jvm") version "1.9.24"
	id("org.jetbrains.kotlin.plugin.spring") version "1.9.24"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.9.24"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.9.24"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Backend Kotlin Spring"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// H2 database
	runtimeOnly("com.h2database:h2")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("com.h2database:h2")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
