dependencies {
    api(project(":batch-domain"))
    api("org.springframework.boot:spring-boot-starter-batch")

    runtimeOnly("mysql:mysql-connector-java")

    implementation("org.springframework.batch:spring-batch-test")
}

tasks.bootJar {
    enabled = true
}
