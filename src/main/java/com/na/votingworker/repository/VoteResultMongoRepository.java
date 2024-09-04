package com.na.votingworker.repository;

import com.na.votingworker.entity.VoteResult;
import com.na.votingworker.enums.AnimalEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteResultMongoRepository extends MongoRepository<VoteResult, AnimalEnum> {

    Optional<VoteResult> findByAnimal(AnimalEnum animalEnum);
}
