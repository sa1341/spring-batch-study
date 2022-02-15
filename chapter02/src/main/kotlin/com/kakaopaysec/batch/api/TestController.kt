package com.kakaopaysec.batch.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class TestController {

    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }
}
