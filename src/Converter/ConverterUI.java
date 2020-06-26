package Converter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConverterUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Mass Converter");
        Label kgLabel = new Label("Kilograms (kgs)");
        Label lbLabel = new Label("Pounds (lbs)");
        Label converterLabel = new Label("Mass Converter");
        Label equals = new Label("=");

        TextField kg = new TextField();
        TextField lb = new TextField();
        kg.setPromptText("Enter kg here");
        lb.setPromptText("Enter lb here");

        kg.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!kg.isFocused()) {
                return;
            }

            if (kg.getText().isEmpty()) {
                lb.setText(kg.getText());
                return;
            }

            double kilogram;
            try {
                kilogram = Double.parseDouble(newValue);
            }
            catch (Exception e) {
                return;
            }

            double pound = Controller.kgTolb(kilogram);
            lb.setText(Double.toString(pound));
        });

        lb.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!lb.isFocused()) {
                return;
            }

            if (lb.getText().isEmpty()) {
                kg.setText(lb.getText());
                return;
            }

            double pound;
            try {
                pound = Double.parseDouble(newValue);
            }
            catch (Exception e) {
                return;
            }
            double kilogram = Controller.lbTokg(pound);
            kg.setText(Double.toString(kilogram));
        });


        Button close = new Button("Close");
        close.setOnAction(e -> primaryStage.close());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        GridPane.setConstraints(converterLabel, 2, 0);
        GridPane.setConstraints(kg, 0, 3);
        GridPane.setConstraints(equals, 2, 3);
        GridPane.setHalignment(equals, HPos.CENTER);
        GridPane.setConstraints(lb, 4, 3);
        GridPane.setConstraints(kgLabel, 0, 4);
        GridPane.setHalignment(kgLabel, HPos.CENTER);
        GridPane.setConstraints(lbLabel, 4, 4);
        GridPane.setHalignment(lbLabel, HPos.CENTER);
        GridPane.setConstraints(close, 2, 6);
        GridPane.setHalignment(close, HPos.CENTER);
        grid.getChildren().addAll(converterLabel, kg, equals, lb, kgLabel, lbLabel, close);


        Scene scene = new Scene(grid, 440, 160);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
