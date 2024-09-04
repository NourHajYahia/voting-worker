package com.na.votingworker.entity;

import com.na.votingworker.enums.AnimalEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("VoteResult")
public class VoteResult implements Serializable {
    @Id
    private String id;
    @Indexed(unique = true)
    private AnimalEnum animal;
    private Integer count;

    public VoteResult(AnimalEnum animal) {
        this.animal = animal;
        this.count = 1;
    }


    public AnimalEnum getAnimalEnum() {
        return animal;
    }

    public void setAnimalEnum(AnimalEnum animal) {
        this.animal = animal;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
