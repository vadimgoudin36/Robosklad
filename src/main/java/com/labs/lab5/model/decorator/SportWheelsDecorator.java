package com.labs.lab5.model.decorator;

import com.labs.lab5.model.VehicleComponent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SportWheelsDecorator extends VehicleDecorator {

    public SportWheelsDecorator(VehicleComponent vehicle) {
        super(vehicle);
    }

    @Override
    public void operation(Pane pane) {
        super.operation(pane);

        Circle leftWheel = new Circle(130, 195, 6);
        Circle rightWheel = new Circle(230, 195, 6);

        leftWheel.setFill(Color.GRAY);
        rightWheel.setFill(Color.GRAY);

        pane.getChildren().addAll(leftWheel, rightWheel);
    }

    @Override
    public String decorate() {
        return vehicle.decorate() + ", спортивные колёса";
    }

    @Override
    public float cost() {
        return vehicle.cost() + 1500;
    }
}
