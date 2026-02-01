package com.labs.courseProject.model;

import java.util.List;

public class TOrder extends TWarehouseEntity {

    private List<TStorageRack> racks;

    public TOrder(String name, List<TStorageRack> racks) {
        super(name);
        this.racks = racks;
    }

    public List<TStorageRack> getRacks() {
        return racks;
    }

    @Override
    public String toString() {
        return name;
    }
}

