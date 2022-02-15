plugins {
	id("org.springframework.boot") version "2.5.9"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.32"
	kotlin("plugin.spring") version "1.5.32"
	kotlin("plugin.jpa") version "1.5.32"
}

group = "com.kakaopaysec"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

allprojects{
	repositories {
		jcenter()
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring")
	apply(plugin = "kotlin-kapt")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin-kapt")

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.springframework.boot:spring-boot-starter-batch")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		runtimeOnly("com.h2database:h2")
		testImplementation("org.springframework.batch:spring-batch-test")
		testImplementation("org.springframework.boot:spring-boot-starter-test") {
			exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		}
	}

	tasks.bootJar {
		enabled = false
	}

	tasks.jar {
		enabled = true
	}

	tasks.compileKotlin {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	tasks.compileTestKotlin {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	tasks.test {
		useJUnitPlatform()
		systemProperty("spring.profiles.active", "test")
		maxHeapSize = "2g"
	}
}

tasks.bootJar {
	enabled = false
}
