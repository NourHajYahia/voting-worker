package com.na.votingworker.repository.redis;

import com.na.votingworker.entity.Vote;
import reactor.core.publisher.Mono;

public class VoteRedisRepositoryImpl implements VoteRedisRepository{
//    private final ReactiveRedisTemplate<String, Vote> redisTemplate;
//    private final ReactiveValueOperations<String, Vote> valueOperations;
    @Override
    public Mono<Vote> findById(String id) {
        return null;
    }

    @Override
    public Mono<Boolean> deleteById(String id) {
        return null;
    }
}
