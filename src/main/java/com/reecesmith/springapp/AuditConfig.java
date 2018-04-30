package com.reecesmith.springapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@Configuration
@EnableJpaAuditing
public class AuditConfig
{
    @Bean
    public SpringDataDialect springDataDialect()
    {
        SpringDataDialect s = new SpringDataDialect();


        return s;
    }
}
