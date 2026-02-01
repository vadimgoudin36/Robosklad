package com.labs.lab5.model.decorator;

import com.labs.lab5.model.VehicleComponent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TintedWindowsDecorator extends VehicleDecorator {

    public TintedWindowsDecorator(VehicleComponent vehicle) {
        super(vehicle);
    }

    @Override
    public void operation(Pane pane) {
        super.operation(pane);

        Rectangle window = new Rectangle(
                160,
                125,
                60,
                20
        );
        window.setFill(Color.rgb(0, 0, 0, 0.5));

        pane.getChildren().add(window);
    }

    @Override
    public String decorate() {
        return vehicle.decorate() + ", тонировка";
    }

    @Override
    public float cost() {
        return vehicle.cost() + 800;
    }
}
