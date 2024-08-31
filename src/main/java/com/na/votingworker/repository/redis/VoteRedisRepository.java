package com.na.votingworker.repository.redis;

import com.na.votingworker.entity.Vote;
import reactor.core.publisher.Mono;

public interface VoteRedisRepository {

    Mono<Vote> findById(String id);

    Mono<Boolean> deleteById(String id);


}
