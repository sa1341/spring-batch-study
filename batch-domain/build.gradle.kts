plugins {
    kotlin("plugin.jpa")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("com.querydsl:querydsl-jpa")
    api(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")
    kapt("com.querydsl:querydsl-apt:4.2.1:jpa")
    runtimeOnly ("mysql:mysql-connector-java")
}


tasks.bootJar {
    enabled = false
}
