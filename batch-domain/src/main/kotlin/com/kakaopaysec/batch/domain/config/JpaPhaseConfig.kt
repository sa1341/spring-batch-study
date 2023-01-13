package com.kakaopaysec.batch.domain.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
internal class JpaPhaseConfig(
    val environment: Environment
) {

    @Bean
    fun jpaPhase(): Phase {
        return Phase.values().firstOrNull { phase ->
            environment.activeProfiles.firstOrNull {
                phase.name == it.toUpperCase()
            } != null
        } ?: throw IllegalStateException(
            "Fail to parse phase from active profiles. current active Profiles: " +
                    "${environment.activeProfiles.joinToString(" ")}"
        )
    }
}

enum class Phase {
    LOCAL, DEV, SANDBOX,
    BETA, PROD
}
