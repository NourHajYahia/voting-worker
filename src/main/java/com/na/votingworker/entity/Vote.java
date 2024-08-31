package com.na.votingworker.entity;

import java.io.Serializable;

public class Vote implements Serializable {
    private String id;
    private String animal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
