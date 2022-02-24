package com.kakaopaysec.batch

import com.kakaopaysec.batch.domain.config.EnableBatchDomain
import com.kakaopaysec.batch.validator.ParameterValidator
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.CompositeJobParametersValidator
import org.springframework.batch.core.job.DefaultJobParametersValidator
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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
    fun validator(): CompositeJobParametersValidator {

        val validator = CompositeJobParametersValidator()

        val defaultJobParametersValidator = DefaultJobParametersValidator(arrayOf("fileName"), arrayOf("name", "run.id"))

        defaultJobParametersValidator.afterPropertiesSet()

        validator.setValidators(
            listOf(ParameterValidator(), defaultJobParametersValidator)
        )

        return validator
    }

    @Bean
    fun job(): Job {
        return this.jobBuilderFactory.get("job")
            .validator(validator())
            .start(step1())
            .incrementer(RunIdIncrementer())
            .build()
    }

    @Bean
    fun step1(): Step {
        return this.stepBuilderFactory.get("step1")
            .tasklet(helloWorldTasklet(null, null))
            .build()
    }

    @StepScope
    @Bean
    fun helloWorldTasklet(
        @Value("#{jobParameters['name']}") name: String?,
        @Value("#{jobParameters['fileName']}") fileName: String?
    ): Tasklet {
        return Tasklet { contribution: StepContribution, chunkContext: ChunkContext ->
            println(String.format("Hello, %s!", name))
            println(String.format("fileName = %s", fileName))
            RepeatStatus.FINISHED
        }
    }

}

fun main(args: Array<String>) {
    runApplication<BatchRunnerApplication>(*args)
}








