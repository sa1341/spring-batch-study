package com.kakaopaysec.batch

import com.kakaopaysec.batch.domain.config.EnableBatchDomain
import com.kakaopaysec.batch.service.CustomService
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@EnableBatchDomain
@EnableBatchProcessing
@SpringBootApplication
class MethodInvokingTaskletConfiguration(
    @Autowired private val jobBuilderFactory: JobBuilderFactory,
    @Autowired private val stepBuilderFactory: StepBuilderFactory
)
{

    @Bean
    fun methodInvokingJob(): Job {
        return this.jobBuilderFactory.get("methodInvokingJob")
            .start(methodInvokingStep())
            .build()
    }

    @Bean
    fun methodInvokingStep(): Step {
        return this.stepBuilderFactory.get("methodInvokingStep")
            .tasklet(methodInvokingTasklet(null))
            .build()
    }

    @StepScope
    @Bean
    fun methodInvokingTasklet(@Value("#{jobParameters['message']}") message: String?): MethodInvokingTaskletAdapter {

    val methodInvokingTaskletAdapter = MethodInvokingTaskletAdapter()

        methodInvokingTaskletAdapter.setTargetObject(customService())
        methodInvokingTaskletAdapter.setTargetMethod("serviceMethod")
        methodInvokingTaskletAdapter.setArguments(arrayOf(message))

        return methodInvokingTaskletAdapter
    }

    @Bean
    fun customService(): CustomService {
        return CustomService()
    }
}

fun main(args: Array<String>) {
    runApplication<MethodInvokingTaskletConfiguration>(*args)
}
