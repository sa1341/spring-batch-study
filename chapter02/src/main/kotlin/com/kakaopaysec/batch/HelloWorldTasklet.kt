package com.kakaopaysec.batch

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value

const val HELLO_WORLD = "Hello, %s"
class HelloWorldTasklet: Tasklet {

    @Value("#{jobParameters['name']}")
    private val name: String? = null

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {

        val jobExecutionContext = chunkContext.stepContext
            .stepExecution
            .jobExecution
            .executionContext

        jobExecutionContext.put("user.name", name)

        println(String.format(HELLO_WORLD, name))

        return RepeatStatus.FINISHED
    }
}
