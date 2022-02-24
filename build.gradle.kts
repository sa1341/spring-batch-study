plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa") apply false
	kotlin("kapt")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "com.kakaopaysec.batch"

allprojects{
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring")
	apply(plugin = "kotlin-kapt")
	apply(plugin = "maven")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin-kapt")

	dependencies {
		implementation(kotlin("stdlib-jdk8"))
		implementation(kotlin("reflect"))
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

		implementation("org.springframework.boot:spring-boot-starter")
		kapt("org.springframework.boot:spring-boot-configuration-processor")

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

	group = "com.kakaopaysec.batch"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_1_8

}

tasks.bootJar {
	enabled = false
}
