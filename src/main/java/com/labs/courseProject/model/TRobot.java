package com.labs.courseProject.model;

import java.util.List;

public class TRobot extends TWarehouseEntity {

    private static final double BASE_X = 0;
    private static final double BASE_Y = 0;

    private double x;
    private double y;
    private double battery;

    public TRobot(String name) {
        super(name);
        reset();
    }

    public void reset() {
        x = 0;
        y = 0;
        battery = 100;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getBattery() { return battery; }

    public void moveTo(TStorageRack rack) {
        double dist = distanceTo(rack.getX(), rack.getY());
        battery -= dist * 2;

        x = rack.getX();
        y = rack.getY();

        rack.takeLoad();
    }

    public boolean isAtBase() {
        return x == BASE_X && y == BASE_Y;
    }

    public void returnToBase() {
        double dist = Math.hypot(x - BASE_X, y - BASE_Y);
        battery -= dist * 0.5;
        x = BASE_X;
        y = BASE_Y;
    }

    private double distanceTo(double x2, double y2) {
        return Math.hypot(x - x2, y - y2);
    }
}


