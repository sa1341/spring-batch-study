package com.kakaopaysec.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*
import kotlin.collections.ArrayList

@EnableBatchProcessing
@SpringBootApplication
class ChunkJob (
    @Autowired private val jobBuilderFactory: JobBuilderFactory,
    @Autowired private val stepBuilderFactory: StepBuilderFactory
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
            .chunk<String, String>(1000)
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
                println(">> current item = ${it}")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ChunkJob>(*args)
}
