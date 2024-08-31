package com.na.votingworker.dao;

import com.na.votingworker.entity.VoteResult;
import com.na.votingworker.enums.AnimalEnum;
import com.na.votingworker.repository.VoteResultMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class VoteResultDaoImpl implements VoteResultDao {

    private final VoteResultMongoRepository voteResultMongoRepository;

    @Autowired
    public VoteResultDaoImpl(VoteResultMongoRepository voteResultMongoRepository) {
        this.voteResultMongoRepository = voteResultMongoRepository;
    }

    @Override
    public void increaseVoteResultByAnimal(AnimalEnum animalEnum) {
        Optional<VoteResult> voteResultDb = voteResultMongoRepository.findById(animalEnum);
        if (voteResultDb.isEmpty()) {
            VoteResult voteResult = new VoteResult(animalEnum);
            voteResultMongoRepository.save(voteResult);
            return;
        }
        VoteResult voteResult = voteResultDb.get();
        voteResult.setCount(voteResult.getCount() + 1);
        voteResultMongoRepository.save(voteResult);
    }
}
