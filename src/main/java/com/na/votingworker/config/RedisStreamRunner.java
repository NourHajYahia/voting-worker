package com.na.votingworker.config;

import com.na.votingworker.service.RedisStreamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RedisStreamRunner implements CommandLineRunner {

    private final RedisStreamService listenerService;

    public RedisStreamRunner(RedisStreamService listenerService) {
        this.listenerService = listenerService;
    }

    @Override
    public void run(String... args) throws Exception {
        listenerService.listenToStream("votingstream");
    }
}
