package com.kakaopaysec.batch.listener

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.annotation.AfterJob
import org.springframework.batch.core.annotation.BeforeJob

const val START_MESSAGE = "%s is beginning execution"
const val END_MESSAGE = "%s has completed with the status %s"

class JobLoggerListener {

    @BeforeJob
    fun beforeJob(jobExecution: JobExecution) {
        println(
            String.format(
                START_MESSAGE,
                jobExecution.jobInstance.jobName
            )
        )
    }

    @AfterJob
    fun afterJob(jobExecution: JobExecution) {

        println(
            String.format(
                END_MESSAGE,
                jobExecution.jobInstance.jobName,
                jobExecution.status
            )
        )
    }
}
