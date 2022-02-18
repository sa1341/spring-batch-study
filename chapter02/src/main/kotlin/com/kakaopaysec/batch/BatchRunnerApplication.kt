package com.kakaopaysec.batch

import com.kakaopaysec.batch.config.EnableBatchDomain
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@EnableBatchDomain
@EnableBatchProcessing
@SpringBootApplication
class BatchRunnerApplication(
    @Autowired private val jobBuilderFactory: JobBuilderFactory,
    @Autowired private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun step(): Step {
        return this.stepBuilderFactory.get("step1")
            .tasklet(Tasklet { contribution, chunkContext ->
                println("Hello, World")
                RepeatStatus.FINISHED
            }).build()
    }

    @Bean
    fun job(): Job {
        return this.jobBuilderFactory.get("job")
            .start(step())
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<BatchRunnerApplication>(*args)
}








