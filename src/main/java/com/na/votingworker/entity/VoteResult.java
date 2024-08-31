package com.na.votingworker.entity;

import com.na.votingworker.enums.AnimalEnum;

import java.io.Serializable;

public class VoteResult implements Serializable {

    private AnimalEnum animalEnum;
    private Integer count;


    public AnimalEnum getAnimalEnum() {
        return animalEnum;
    }

    public void setAnimalEnum(AnimalEnum animalEnum) {
        this.animalEnum = animalEnum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
