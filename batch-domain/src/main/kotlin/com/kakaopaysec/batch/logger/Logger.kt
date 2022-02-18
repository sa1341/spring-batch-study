package com.kakaopaysec.batch.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <A : Any> A.logger(): Lazy<Logger> = lazy { LoggerFactory.getLogger(this.javaClass) }
