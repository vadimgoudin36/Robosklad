package com.labs.courseProject.model;

public class TStorageRack extends TWarehouseEntity {

    private double x;
    private double y;
    private int load;
    private boolean reserved = false;

    public TStorageRack(String name, double x, double y, int load) {
        super(name);
        this.x = x;
        this.y = y;
        this.load = load;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getLoad() { return load; }

    public boolean isEmpty() {
        return load <= 0;
    }

    public void takeLoad() {
        load = 0;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve() {
        this.reserved = true;
    }

    @Override
    public String toString() {
        return name;
    }
}

