package com.labs.lab5.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CarBase implements VehicleComponent {

    @Override
    public void operation(Pane pane) {

        pane.getChildren().clear();

        // === Кузов ===
        Rectangle body = new Rectangle(100, 150, 160, 40);
        body.setFill(Color.RED);
        body.setStroke(Color.BLACK);

        // === Кабина ===
        Rectangle cabin = new Rectangle(150, 120, 80, 30);
        cabin.setFill(Color.DARKRED);
        cabin.setStroke(Color.BLACK);

        // === Колёса ===
        Circle wheelLeft = new Circle(130, 195, 12);
        Circle wheelRight = new Circle(230, 195, 12);
        wheelLeft.setFill(Color.BLACK);
        wheelRight.setFill(Color.BLACK);

        pane.getChildren().addAll(
                body,
                cabin,
                wheelLeft,
                wheelRight
        );
    }

    @Override
    public String decorate() {
        return "Базовая машина";
    }

    @Override
    public float cost() {
        return 10000;
    }
}
