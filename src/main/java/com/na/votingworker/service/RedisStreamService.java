package com.na.votingworker.service;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RedisStreamService {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public RedisStreamService(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void startProcessing() {

        Flux<ObjectRecord<String, String>> stream = redisTemplate
                .opsForStream()
                .read(String.class, StreamOffset.fromStart("your-stream-key"));

        stream.flatMap(record -> {
                    String id = record.getId().getValue();
                    System.out.println("Processing record ID: " + id);
                    String value = record.getValue();
                    // Add further processing logic here
                    return Flux.empty(); // Return an empty Flux if no further processing is needed
                })
                .subscribe(
                        success -> System.out.println("Successfully processed records"),
                        error -> System.err.println("Error during stream processing: " + error)
                );
    }
}