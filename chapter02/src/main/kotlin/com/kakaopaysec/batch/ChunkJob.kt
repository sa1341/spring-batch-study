package com.kakaopaysec.batch

/*
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.batch.repeat.CompletionPolicy
import org.springframework.batch.repeat.policy.CompositeCompletionPolicy
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy
import org.springframework.batch.repeat.policy.TimeoutTerminationPolicy
import org.springframework.context.annotation.Bean
import java.util.*

class ChunkJob (
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun chunkBasedJob(): Job {
        return this.jobBuilderFactory.get("chunkBasedJob")
            .start(chunkStep())
            .build()
    }

    @Bean
    fun chunkStep(): Step {
        return this.stepBuilderFactory.get("chunkStep")
            .chunk<String, String>(completionPolicy())
            .reader(itemReader())
            .writer(itemWriter())
            .build()
    }

    @Bean
    fun itemReader(): ListItemReader<String> {

        var items: ArrayList<String> = arrayListOf()

        for (i in 0..100000) {
            items.add(UUID.randomUUID().toString())
        }

        return ListItemReader(items)
    }

    @Bean
    fun itemWriter(): ItemWriter<String> {
        return ItemWriter { it ->
            it.forEach {
                println(">> current item = $it")
            }
        }
    }

    @Bean
    fun completionPolicy(): CompletionPolicy {

        val policy = CompositeCompletionPolicy()

        val completionPolicies = arrayOf(
            TimeoutTerminationPolicy(3),
            SimpleCompletionPolicy(1000))

        policy.setPolicies(completionPolicies)

        return policy
    }
}
*/
