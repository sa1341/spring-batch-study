package com.kakaopaysec.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BatchRunnerApplication

fun main(args: Array<String>) {
    runApplication<BatchRunnerApplication>(*args)
}
