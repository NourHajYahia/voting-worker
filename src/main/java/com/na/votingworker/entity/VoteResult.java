package com.na.votingworker.entity;

import com.na.votingworker.enums.AnimalEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("VoteResult")
public class VoteResult implements Serializable {

    @Id
    private AnimalEnum animalEnum;
    private Integer count;

    public VoteResult(AnimalEnum animalEnum) {
        this.animalEnum = animalEnum;
        this.count = 0;
    }


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
