package com.kakaopaysec.batch.domain.config

import com.kakaopaysec.batch.logger.logger
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.stereotype.Component
import java.util.TimeZone
import javax.sql.DataSource

@Configuration
@EnableJpaAuditing
class BatchDataSourceConfig {

    private val log by logger()

    private val DEFAULT_TIME_ZONE = TimeZone.getTimeZone("Asia/Seoul");

    init {
        initTimeZone()
    }

    @Bean
    fun dataSource(
        properties: BatchDataSourceProperties
    ): DataSource {
        return DataSourceBuilder.create()
            .url(properties.jdbcUrl)
            .driverClassName(properties.driverClassName)
            .username(properties.username)
            .password(properties.password)
            .type(HikariDataSource::class.java)
            .build()
    }

    @Component
    @ConfigurationProperties("spring.batch.datasource")
    @PropertySources(
        PropertySource(value = ["classpath:/datasource/spring-batch-datasource-\${spring.profiles.active}.properties"])
    )
    class BatchDataSourceProperties: HikariConfig()

    /**
     * Sets the default time zone.
     */
    private fun initTimeZone() {

        val userTimezone = System.getProperty("user.timezone")

        if (userTimezone != null && DEFAULT_TIME_ZONE.id != userTimezone) {
            log.warn(
                "VM option [user.timezone={}] will be ignored",
                userTimezone
            )
        }

        TimeZone.setDefault(DEFAULT_TIME_ZONE)

        if (TimeZone.getDefault() != DEFAULT_TIME_ZONE) {
            throw AssertionError(
                String.format(
                    "Expected [%s] but was [%s]",
                    DEFAULT_TIME_ZONE.id,
                    TimeZone.getDefault().id
                )
            )
        }
        log.info(
            "Set default time zone to [{}]",
            TimeZone.getDefault().id
        )
    }

}
