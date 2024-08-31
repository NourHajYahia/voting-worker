package com.na.votingworker.dao;

import com.na.votingworker.enums.AnimalEnum;

public interface VoteResultDao {

    void increaseVoteResultByAnimal(AnimalEnum animalEnum);
}
