dependencies {
    api(project(":batch-domain"))
    api("org.springframework.boot:spring-boot-starter-batch")

    runtimeOnly("mysql:mysql-connector-java")

    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.batch:spring-batch-test")

    runtimeOnly("com.h2database:h2")
    runtimeOnly ("mysql:mysql-connector-java")
}

tasks.bootJar {
    enabled = true
}
