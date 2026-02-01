package com.labs.courseProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CourseProjectApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/labs/course-project/warehouse-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load(), 1400, 700);

        stage.setTitle("Автоматизированный склад — роботы-комплектовщики");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
