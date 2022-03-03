package com.kakaopaysec.batch.listener

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.annotation.AfterJob
import org.springframework.batch.core.annotation.BeforeJob

class JobLoggerListener {

    private val START_MESSAGE = "%s is beginning execution"
    private val END_MESSAGE = "%s has completed with the status %s"

    @BeforeJob
    fun beforeJob(jobExecution: JobExecution) {
        println(String.format(START_MESSAGE,
            jobExecution.jobInstance.jobName))
    }

    @AfterJob
    fun afterJob(jobExecution: JobExecution) {

        println(String.format(END_MESSAGE,
            jobExecution.jobInstance.jobName,
            jobExecution.status))
    }
}
