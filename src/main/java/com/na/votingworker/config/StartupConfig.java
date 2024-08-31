package com.na.votingworker.config;

import com.na.votingworker.service.RedisStreamService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig {

    @Bean
    public ApplicationRunner run(RedisStreamService redisStreamService) {
        return args -> redisStreamService.startProcessing();
    }
}
