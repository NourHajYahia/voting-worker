package com.na.votingworker.repository.mongo;

import com.na.votingworker.entity.VoteResult;
import com.na.votingworker.enums.AnimalEnum;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VoteResultMongoRepository extends ReactiveMongoRepository<AnimalEnum, VoteResult> {
}
