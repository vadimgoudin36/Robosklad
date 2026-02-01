package com.labs.courseProject.model;

public abstract class TWarehouseEntity {

    protected String name;

    public TWarehouseEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

