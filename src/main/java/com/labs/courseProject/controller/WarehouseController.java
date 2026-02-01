package com.labs.courseProject.controller;

import com.labs.courseProject.model.TOrder;
import com.labs.courseProject.model.TRobot;
import com.labs.courseProject.model.TStorageRack;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;

public class WarehouseController {

    @FXML private TableView<TRobot> robotTable;
    @FXML private TableColumn<TRobot, String> robotNameCol;
    @FXML private TableColumn<TRobot, Integer> batteryCol;
    @FXML private TableColumn<TRobot, Integer> capacityCol;
    @FXML private Pane warehousePane;


    @FXML private ListView<TStorageRack> rackList;
    @FXML private ListView<TOrder> orderList;

    @FXML private Label statusLabel;

    private ObservableList<TRobot> robots;
    private ObservableList<TOrder> orders;
    private ObservableList<TStorageRack> racks;


    public WarehouseController() {
        robots = FXCollections.observableArrayList();
        orders = FXCollections.observableArrayList();
        racks  = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        initTestData();
        initTables();
        initLists();
        redrawWarehouse();
    }

    private void initTables() {
        robotNameCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName()));

        batteryCol.setCellValueFactory(data ->
                new SimpleIntegerProperty((int) data.getValue().getBattery()).asObject());

        robotTable.setItems(robots);
        redrawWarehouse();
    }

    private void initLists() {
        orderList.setItems(orders);

        orderList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldOrder, newOrder) -> {
                    if (newOrder != null) {
                        rackList.setItems(
                                FXCollections.observableArrayList(newOrder.getRacks())
                        );
                    }
                }
        );

        rackList.setItems(racks);
    }

    private void initTestData() {

        TStorageRack r1 = new TStorageRack("Rack-A", 2, 3, 5);
        TStorageRack r2 = new TStorageRack("Rack-B", 5, 1, 3);
        TStorageRack r3 = new TStorageRack("Rack-C", 7, 4, 3);
        TStorageRack r4 = new TStorageRack("Rack-D", 1, 6, 4);
        TStorageRack r5 = new TStorageRack("Rack-E", 8, 2, 6);

        racks.addAll(r1, r2, r3, r4, r5);

        orders.addAll(
                new TOrder("Order-1", List.of(r1, r2)),
                new TOrder("Order-2", List.of(r3, r4)),
                new TOrder("Order-3", List.of(r5))
        );

        robots.addAll(
                new TRobot("R1"),
                new TRobot("R2")
        );
    }

    @FXML
    public void startSimulation() {

        for (TRobot robot : robots) {

            // 1. если не на базе — вернуться
            if (!robot.isAtBase()) {
                robot.returnToBase();
                continue;
            }

            // 2. если на базе — выбрать ближайший стеллаж
            TStorageRack nearest = findNearestRack(robot);

            if (nearest != null) {
                robot.moveTo(nearest);
            }
        }

        robotTable.refresh();
        redrawWarehouse();
        statusLabel.setText("Шаг симуляции выполнен");
    }


    private TStorageRack findNearestRack(TRobot robot) {
        return racks.stream()
                .filter(r -> !r.isEmpty())
                .filter(r -> !r.isReserved())
                .min((a, b) -> Double.compare(
                        dist(robot, a),
                        dist(robot, b)
                ))
                .orElse(null);
    }

    private double dist(TRobot r, TStorageRack rack) {
        return Math.hypot(
                r.getX() - rack.getX(),
                r.getY() - rack.getY()
        );
    }


    private void drawRobots() {
        for (TRobot robot : robots) {
            double scale = 40;
            double radius = 12;

            Circle c = new Circle(
                    robot.getX() * scale + radius,
                    robot.getY() * scale + radius,
                    radius
            );
            c.setFill(Color.BLUE);

            Tooltip tooltip = new Tooltip(
                    robot.getName() +
                            "\nЗаряд: " + (int) robot.getBattery() + "%"
            );
            tooltip.setShowDelay(javafx.util.Duration.ZERO);
            Tooltip.install(c, tooltip);

            warehousePane.getChildren().add(c);
        }
    }

    private void drawRacks() {
        for (TStorageRack r : racks) {

            Rectangle rect = new Rectangle(
                    r.getX() * 40,
                    r.getY() * 40,
                    30, 30
            );

            rect.setFill(r.isEmpty() ? Color.GRAY : Color.DARKGREEN);

            rect.setPickOnBounds(true);

            Tooltip tooltip = new Tooltip(
                    r.getName() + " | груз: " + r.getLoad()
            );
            tooltip.setShowDelay(Duration.ZERO);

            Tooltip.install(rect, tooltip);

            warehousePane.getChildren().add(rect);
        }
    }

    private List<TStorageRack> getAllRacks() {
        return orders.stream()
                .flatMap(o -> o.getRacks().stream())
                .toList();
    }

    private void redrawWarehouse() {
        warehousePane.getChildren().clear();
        drawRacks();
        drawRobots();
    }

}
